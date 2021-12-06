package com.edoe.api.Controllers;

import com.edoe.api.dto.ItemDTO;
import com.edoe.api.dto.ItemRequiredDTO;
import com.edoe.api.entity.Donation;
import com.edoe.api.entity.ItemRequired;
import com.edoe.api.services.DoacoesService;
import com.edoe.api.services.ItemRequiredService;
import com.edoe.api.services.exceptions.NotCredentialException;
import com.edoe.api.services.exceptions.RepeatedNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ItemRequiredController {

    @Autowired
    private ItemRequiredService itemRequiredService;

    @Autowired
    private DoacoesService doacoesService;


    @PostMapping("/v1/api/edoe/item-required")
    public ResponseEntity<ItemRequiredDTO> addItemRequired(@RequestBody ItemRequired item, @RequestHeader("Authorization") String header ) throws NotCredentialException, RepeatedNameException {
        return ResponseEntity.ok(itemRequiredService.saveItemReq(header, item));
    }

    @DeleteMapping("/v1/api/edoe/item-required/{id}")
    public ResponseEntity<?> removeItemRequired(@PathVariable Long id, @RequestHeader("Authorization") String header ) throws NotCredentialException {
        itemRequiredService.removeItemRequired(id, header);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/v1/api/edoe/item-required/{id}")
    public ResponseEntity<ItemRequiredDTO> updateItemRequired(@PathVariable Long id ,@RequestBody ItemRequired item, @RequestHeader("Authorization") String header) throws NotCredentialException {
        return ResponseEntity.ok().body(itemRequiredService.updateItemRequired(id, item, header));
    }

    @GetMapping("/v1/api/edoe/item-required/descriptor/{id}")
    public ResponseEntity<List<ItemRequiredDTO>> itemsRequiredByDescriptor(@PathVariable Long id) {
        return ResponseEntity.ok(itemRequiredService.getAll(id));
    }

    @GetMapping("/v1/api/edoe/item-required/amount")
    public ResponseEntity<List<ItemRequiredDTO>> itemsRequiredAmount() {
        return ResponseEntity.ok(itemRequiredService.getMaxAmout());
    }

    @GetMapping("/v1/api/edoe/item-required/find")
    public ResponseEntity<List<ItemRequiredDTO>> ItemsName(@PathParam( value = "name") String name) throws NotCredentialException {
        return ResponseEntity.ok(itemRequiredService.findDescriptorName(name));
    }

    @PostMapping("/v1/api/edoe/item-required/matches")
    public ResponseEntity<?> findMatches(@RequestHeader("Authorization")String header, @RequestBody ItemRequired itemRequired) throws NotCredentialException {
        List<ItemDTO> list = doacoesService.matchesItens(header,itemRequired);
        return list == null ?  ResponseEntity.notFound().build() :  ResponseEntity.ok(doacoesService.matchesItens(header,itemRequired));
    }

    @PostMapping("/v1/api/edoe/donation/{idItemRequerid}/{idItem}/{amount}")
    public void donation(@RequestHeader("Authorization") String header, @PathVariable(value = "idItemRequerid") Long idItemRequired, @PathVariable(value = "idItem") Long idItem, @PathVariable(value = "amount") Integer amount) throws NotCredentialException {
         doacoesService.donation(header,idItemRequired,idItem, amount);
        System.out.println(idItem);
        System.out.println(idItemRequired);
    }

    @GetMapping("/v1/api/edoe/donations")
    public ResponseEntity<List<Donation>> getAllDonation(@RequestHeader("Authorization") String header) {
        return ResponseEntity.ok(doacoesService.getAllDonation(header));
    }
}
