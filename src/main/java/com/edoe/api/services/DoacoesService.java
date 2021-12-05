package com.edoe.api.services;

import com.edoe.api.dto.ItemDTO;
import com.edoe.api.dto.ItemDonationDTO;
import com.edoe.api.dto.UserDTO;
import com.edoe.api.entity.Donation;
import com.edoe.api.entity.Item;
import com.edoe.api.entity.ItemRequired;
import com.edoe.api.entity.User;
import com.edoe.api.repositories.DonationRepository;
import com.edoe.api.repositories.ItemRepository;
import com.edoe.api.repositories.ItemRequiredRepository;
import com.edoe.api.services.exceptions.NotCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoacoesService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRequiredService itemRequiredService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemRequiredRepository itemRequiredRepository;

    @Autowired
    DonationRepository donationRepository;




    public List<ItemDTO> matchesItens(String authorization, ItemRequired itemRequired) throws NotCredentialException {
        if(usuarioService.usuarioTemPermissaoReceptor(authorization,  usuarioService.UsuarioDoToken(authorization))) {
            String descriptorItemRequired = itemRequired.getDescriptor().getName();
            return itemService.findDescriptorName(descriptorItemRequired);
        }
        throw new NotCredentialException("Não foi encontrado o matches");
    }

    public void donation(String authorization,Long idItemRequired, Long idItem, Integer amount ) throws NotCredentialException {
        if(usuarioService.usuarioTemPermissaoReceptor(authorization,  usuarioService.UsuarioDoToken(authorization))) {
            if(descriptorEquals(idItemRequired, idItem)){
                ItemRequired itemRequired = itemRequiredService.getItemRequeridByid(idItemRequired);
                Item item = itemService.getItemRequeridByid(idItem);
                itemRequired.setAmount(itemRequired.getAmount() - amount);
                item.setAmount(item.getAmount() - amount);
                if(item.getAmount() == 0){
                    itemRepository.delete(item);
                }
                if( itemRequired.getAmount() == 0){
                    itemRequiredRepository.delete(itemRequired);
                }
                Donation donation = new Donation();
                donation.setDate(LocalDate.now());
                donation.setAmountDonation(amount);
                donation.setUserDonation(item.getUser().getEmail());
                donation.setUserReceptor(itemRequired.getUser().getEmail());
                donation.setDetailItem(itemRequired.getMotivation());
                donationRepository.save(donation);
            }
        } else{
            throw new NotCredentialException("Deu ruim");
        }
    }

    public List<Donation> getAllDonation(String authorization){
        return donationRepository.findAll();
    }

    public boolean descriptorEquals(Long idItemRequired, Long idItem){
        ItemRequired itemRequired = itemRequiredService.getItemRequeridByid(idItemRequired);
        Item item = itemService.getItemRequeridByid(idItem);
        if(item.getDescriptor().getName().equals(item.getDescriptor().getName())){
            return true;
        };
        return false;
    }

}
