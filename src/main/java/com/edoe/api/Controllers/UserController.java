package com.edoe.api.Controllers;

import com.edoe.api.entity.User;
import com.edoe.api.enums.Role;
import com.edoe.api.services.UsuarioService;
import com.edoe.api.services.exceptions.NotCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PatchMapping ("/v1/api/edoe/{id}")
    public ResponseEntity<User> updateRoleWithId(@PathVariable(value = "id") Long id, @RequestBody User userRole, @RequestHeader("Authorization") String header) throws NotCredentialException {
        User userupdate = userService.updateRole(id, userRole, header);
        return  new ResponseEntity<>(userupdate, HttpStatus.OK);
    }

}
