package com.edoe.api.Controllers;

import com.edoe.api.entity.Descriptor;
import com.edoe.api.services.DescriptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class DescriptorController {

    @Autowired
    private DescriptorService descriptorService;

    @PostMapping("/v1/api/edoe/descriptor")
    public ResponseEntity<Descriptor> saveDescriptor(@RequestBody Descriptor descriptor, @RequestHeader("authorization") String header) throws Exception {

        Descriptor desc = descriptorService.saveDescriptor(descriptor, header);
        return desc == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(desc);
    }

    @GetMapping("/v1/api/edoe/descriptors")
    public ResponseEntity<List<Descriptor>> getAllDescriptors(){
          return ResponseEntity.ok(descriptorService.getDescriptors());
    }

}
