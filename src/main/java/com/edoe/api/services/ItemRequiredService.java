package com.edoe.api.services;

import com.edoe.api.dto.ItemRequiredDTO;
import com.edoe.api.entity.Descriptor;
import com.edoe.api.entity.ItemRequired;
import com.edoe.api.entity.User;
import com.edoe.api.enums.Role;
import com.edoe.api.repositories.ItemRequiredRepository;
import com.edoe.api.services.exceptions.NotCredentialException;
import com.edoe.api.services.exceptions.RepeatedNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemRequiredService {

    @Autowired
    private ItemRequiredRepository itemRequiredRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DescriptorService descriptorService;

    public ItemRequiredDTO saveItemReq(String authorization , ItemRequired item) throws NotCredentialException, RepeatedNameException {
        User user = usuarioService.UsuarioDoTokenItem(authorization);

        if(usuarioService.usuarioTemPermissaoReceptor(authorization,  usuarioService.UsuarioDoToken(authorization))){
            if( user.getRole() == Role.APENAS_RECEPTOR){
                if(descriptorService.existDescriptor(item.getDescriptor())){
                    throw new RepeatedNameException("Descritor ja existente");
                }
                item.setUser(user);
                ItemRequired itemSave =  itemRequiredRepository.save(item);
                return new ItemRequiredDTO(itemSave);
            }
        }
        throw new NotCredentialException("Usuario não pode cadastrar");
    }


    public void removeItemRequired(Long id, String authorization) throws NotCredentialException {
        ItemRequired itemRemove = itemRequiredRepository.getById(id);
        if(usuarioService.usuarioTemPermissaoReceptor(authorization,  usuarioService.UsuarioDoToken(authorization))){
            if(itemRemove.getUser().getRole() == Role.APENAS_RECEPTOR){
                if(itemRemove.getUser().getEmail().equals(usuarioService.UsuarioDoTokenItem(authorization).getEmail())){
                    itemRemove.setDeleted(true);
                    itemRequiredRepository.save(itemRemove);

                }
            }
        }else {
            throw new NotCredentialException("Usuario não pode remove");
        }
    }

    public ItemRequiredDTO updateItemRequired(Long id, ItemRequired item, String authorization) throws NotCredentialException {

        ItemRequired itemUpdate = itemRequiredRepository.getById(id);
        if(usuarioService.usuarioTemPermissaoReceptor(authorization,  usuarioService.UsuarioDoToken(authorization))) {
            if(itemUpdate.getUser().getRole() == Role.APENAS_RECEPTOR){
                if(itemUpdate.getUser().getEmail().equals(usuarioService.UsuarioDoTokenItem(authorization).getEmail())){
                    ItemRequired itemreq = itemRequiredRepository.getById(id);
                    itemreq.setAmount(item.getAmount());
                    itemreq.setMotivation(item.getMotivation());
                    ItemRequired itemsave = itemRequiredRepository.save(itemreq);
                    return new ItemRequiredDTO(itemsave);
                }
            }

        }
        throw new NotCredentialException("Usuario não tem as credenciais de acesso !");
    }
}
