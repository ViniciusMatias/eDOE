package com.edoe.api.services;

import com.edoe.api.dto.DescriptorDTO;
import com.edoe.api.dto.ItemDTO;
import com.edoe.api.entity.Descriptor;
import com.edoe.api.entity.Item;
import com.edoe.api.entity.User;
import com.edoe.api.enums.Role;
import com.edoe.api.repositories.ItemRepository;
import com.edoe.api.services.exceptions.NotCredentialException;
import com.edoe.api.services.exceptions.RepeatedNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DescriptorService descriptorService;

    public ItemDTO saveItem(String authorization , Item item) throws NotCredentialException, RepeatedNameException {
        User user = usuarioService.UsuarioDoTokenItem(authorization);
        if(usuarioService.usuarioTemPermissaoDoador(authorization,  usuarioService.UsuarioDoToken(authorization))){
            if( user.getRole() == Role.APENAS_DOADOR){

                if(descriptorService.existDescriptor(item.getDescriptor())){
                    throw new RepeatedNameException("Descritor ja existente");
                }
                item.setUser(user);
                Item itemSave =  itemRepository.save(item);
                return new ItemDTO(itemSave);
            }
        }
        throw new NotCredentialException("Usuario não pode cadastrar");
    }

  public void remove(Long id, String authorization) throws NotCredentialException {
     Item itemRemove = itemRepository.getById(id);
      if(usuarioService.usuarioTemPermissaoDoador(authorization,  usuarioService.UsuarioDoToken(authorization))){
          if(itemRemove.getUser().getRole() == Role.APENAS_DOADOR){
              if(itemRemove.getUser().getEmail().equals(usuarioService.UsuarioDoTokenItem(authorization).getEmail())){
                  itemRemove.setDeleted(true);
                  itemRepository.save(itemRemove);
              }
          }
      }else {
          throw new NotCredentialException("Usuario não pode remove");
      }
  }

  public ItemDTO update(Long id, Item item, String authorization) throws NotCredentialException {
      User user = usuarioService.UsuarioDoTokenItem(authorization);
      Item itemUpdate = itemRepository.getById(id);
      if(usuarioService.usuarioTemPermissaoDoador(authorization,  usuarioService.UsuarioDoToken(authorization))) {
          if(user.getRole() == Role.APENAS_DOADOR){
              if(itemUpdate.getUser().getEmail().equals(usuarioService.UsuarioDoTokenItem(authorization).getEmail())){
                  Item itemreq = itemRepository.getById(id);
                  itemreq.setAmount(item.getAmount());
                  itemreq.setDetail(item.getDetail());
                  Item itemsave = itemRepository.save(itemreq);
                  return new ItemDTO(itemsave);
              }
          }

      }
      throw new NotCredentialException("Usuario não tem as credenciais de acesso !");
    }


    public List<DescriptorDTO> findAllItem(String authorization, String order)  throws NotCredentialException {
        if(usuarioService.usuarioTemPermissao(authorization,  usuarioService.UsuarioDoToken(authorization))){
           if(order.equals("dsc")){
               return getItemNotDeleted()
                       .stream()
                       .map((items) -> items.getDescriptor())
                       .sorted(Comparator.comparing(DescriptorDTO::getName))
                       .collect(Collectors.toList());
           }
           if (order.equals("asc")){
               return getItemNotDeleted()
                       .stream()
                       .map((items) -> items.getDescriptor())
                       .sorted(Comparator.comparing(DescriptorDTO::getName).reversed())
                       .collect(Collectors.toList());
           }
        }
        throw new NotCredentialException("Usuario não tem as credenciais de acesso !");
    }

    public List<ItemDTO> getAll(Long id) {

        return itemRepository.findAll().stream().filter((e) -> e.getDescriptor().getId() == id).map((e) -> new ItemDTO(e)).collect(Collectors.toList());
    }
    public List<ItemDTO> getMaxAmout() {
        return getItemNotDeleted()
                .stream()
                .sorted(Comparator.comparing(ItemDTO::getAmount).reversed())
                .limit(10)
                .collect(Collectors.toList());

    }

    public List<ItemDTO> findDescriptorName(String name) {
        return itemRepository.findAll().stream()
                .filter(item -> item.getDeleted() == false)
                .filter((item) -> item.getDescriptor().getName().equalsIgnoreCase(name))
                .map(item -> new ItemDTO(item)).collect(Collectors.toList());

    }

    public List<ItemDTO> getItemNotDeleted() {
        return itemRepository.findAll().stream().filter(item -> item.getDeleted() == false).map( item -> new ItemDTO(item)).collect(Collectors.toList());
    }
}
