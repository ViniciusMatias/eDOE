package com.edoe.api.Controllers;


import com.edoe.api.dto.ItemDTO;
import com.edoe.api.entity.Item;
import com.edoe.api.services.ItemService;
import com.edoe.api.services.exceptions.NotCredentialException;
import com.edoe.api.services.exceptions.RepeatedNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping("/v1/api/edoe/item")
    public ResponseEntity<ItemDTO> saveItem(@RequestBody Item item, @RequestHeader("Authorization") String header ) throws NotCredentialException, RepeatedNameException {
        return ResponseEntity.ok(itemService.saveItem(header , item));
    }

    @GetMapping("/v1/api/edoe/items")
    public ResponseEntity<?> getAllItems(@PathParam(value=("name")) String order, @RequestHeader("Authorization") String header) throws NotCredentialException {
        return itemService.findAllItem(header, order) == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(itemService.findAllItem(header, order));
    }
    @DeleteMapping("/v1/api/edoe/item/{id}")
    public ResponseEntity<?> removeItem(@PathVariable Long id, @RequestHeader("Authorization") String header ) throws NotCredentialException {
        itemService.remove(id, header);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/v1/api/edoe/item/{id}")
    public ResponseEntity<ItemDTO> update(@PathVariable Long id ,@RequestBody Item item, @RequestHeader("Authorization") String header) throws NotCredentialException {
       return ResponseEntity.ok().body(itemService.update(id, item, header));
    }

    @GetMapping("/v1/api/edoe/items/descriptor/{id}")
    public ResponseEntity<List<ItemDTO>> items(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getAll(id));
    }

    @GetMapping("/v1/api/edoe/items/amount")
    public ResponseEntity<List<ItemDTO>> itemsAmount() {
        return ResponseEntity.ok(itemService.getMaxAmout());
    }

    @GetMapping("/v1/api/edoe/items/find")
    public ResponseEntity<List<ItemDTO>> ItemsName(@PathParam( value = "name") String name) throws NotCredentialException {
       return ResponseEntity.ok(itemService.findDescriptorName(name));
    }

}
