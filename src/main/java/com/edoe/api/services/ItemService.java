package com.edoe.api.services;

import com.edoe.api.entity.Descriptor;
import com.edoe.api.entity.Item;
import com.edoe.api.enums.Role;
import com.edoe.api.repositories.ItemRepository;
import com.edoe.api.services.exceptions.NotCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Item saveItem(String authorization , Item item) throws NotCredentialException {
        if(usuarioService.usuarioTemPermissaoDoador(authorization,  usuarioService.UsuarioDoToken(authorization))){
            if( item.getUser().getRole() == Role.APENAS_DOADOR){
                return itemRepository.save(item);
            }
        }
        throw new NotCredentialException("Usuario não pode cadastrar");
    }

  public void remove(Long id, String authorization) throws NotCredentialException {
     Item itemRemove = itemRepository.getById(id);
      System.out.println(itemRemove.getUser().getEmail());
      System.out.println(usuarioService.UsuarioDoTokenItem(authorization).getEmail());
      if(usuarioService.usuarioTemPermissaoDoador(authorization,  usuarioService.UsuarioDoToken(authorization))){
          if(itemRemove.getUser().getEmail().equals(usuarioService.UsuarioDoTokenItem(authorization).getEmail())){
              itemRepository.delete(itemRemove);
          }
      }
  }

  public Item update(Long id, Item item, String authorization) throws NotCredentialException {

      if(usuarioService.usuarioTemPermissao(authorization,  usuarioService.UsuarioDoToken(authorization))){
          Item itemreq = itemRepository.getById(id);
          itemreq.setAmount(item.getAmount());
          itemreq.setDetail(item.getDetail());
          return itemRepository.save(itemreq);
      }
      throw new NotCredentialException("Usuario não tem as credenciais de acesso !");
    }


    public Page<Descriptor> findAllItem(String authorization, Pageable pageable)  throws NotCredentialException {
        if(usuarioService.usuarioTemPermissao(authorization,  usuarioService.UsuarioDoToken(authorization))){
            return itemRepository.findAll(pageable).map((items) -> items.getDescriptor());
        }
        throw new NotCredentialException("Usuario não tem as credenciais de acesso !");
    }

}
