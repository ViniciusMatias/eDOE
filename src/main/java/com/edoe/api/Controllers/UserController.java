package com.edoe.api.Controllers;

import com.edoe.api.entity.User;
import com.edoe.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@RestController
public class UserController {

    @Autowired
    UsuarioService userService;

    @PostMapping("/v1/api/edoe")
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestHeader("Authorization") String header) throws Exception {
        User userSave = userService.saveUsuario(header, user);
       return new ResponseEntity<User>(userSave, HttpStatus.OK);
    }

}
