package com.edoe.api.services;

import com.edoe.api.dto.ItemDTO;
import com.edoe.api.entity.Item;
import com.edoe.api.entity.ItemRequired;
import com.edoe.api.services.exceptions.NotCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacoesService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ItemService itemService;

    public List<ItemDTO> matchesItens(String authorization, ItemRequired itemRequired) throws NotCredentialException {
        if(usuarioService.usuarioTemPermissaoReceptor(authorization,  usuarioService.UsuarioDoToken(authorization))) {
            String descriptorItemRequired = itemRequired.getDescriptor().getName();
            return itemService.findDescriptorName(descriptorItemRequired);
        }
        throw new NotCredentialException("");
    }
}
