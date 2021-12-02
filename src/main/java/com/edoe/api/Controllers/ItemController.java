package com.edoe.api.Controllers;


import com.edoe.api.entity.Item;
import com.edoe.api.services.ItemService;
import com.edoe.api.services.exceptions.NotCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping("/v1/api/edoe/item")
    public ResponseEntity<Item> saveItem(@RequestBody Item item, @RequestHeader("Authorization") String header ) throws NotCredentialException {
        return ResponseEntity.ok(itemService.saveItem(header , item));
    }

    @GetMapping("/v1/api/edoe/items")
    public ResponseEntity<?> getAllItems(Pageable pageable, @RequestHeader("Authorization") String header) throws NotCredentialException {
        return ResponseEntity.ok(itemService.findAllItem(header, pageable));
    }
    @DeleteMapping("/v1/api/edoe/item/{id}")
    public ResponseEntity<?> removeItem(@PathVariable Long id, @RequestHeader("Authorization") String header ) throws NotCredentialException {
        itemService.remove(id, header);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/v1/api/edoe/item/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id ,@RequestBody Item item, @RequestHeader("Authorization") String header) throws NotCredentialException {
       return ResponseEntity.ok().body(itemService.update(id, item, header));
    }

}
