package com.edoe.api.Controllers;


import com.edoe.api.dto.UserLogin;
import com.edoe.api.services.ResponseLoginJWT;
import com.edoe.api.services.ServiceJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@RestController
@RequestMapping("/auth")
public class UserLoginController {


    @Autowired
    private ServiceJWT serviceJWT;

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginJWT> autentica(@RequestBody UserLogin user) throws ServletException {
        return new ResponseEntity<ResponseLoginJWT>(serviceJWT.autentica(user), HttpStatus.OK);
    }

}
