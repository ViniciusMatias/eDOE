package com.edoe.api.services;

import com.edoe.api.dto.UserLogin;
import com.edoe.api.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ServiceJWT {

    @Autowired
    private UsuarioService usuarioService;
    public static final String TOKEN_KEY = "wdsjfhkwbfdgwuierhweij";

    public ResponseLoginJWT autentica(UserLogin userLogin) {

        if (!usuarioService.validaUsuarioSenha(userLogin)) {
            return new ResponseLoginJWT("Usuario ou senha invalidos. Nao foi realizado o login.");
        }

        String token = geraToken(userLogin.getEmail());
        return new ResponseLoginJWT(token);
    }

    private String geraToken(String email) {
        return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(email)
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();// 3 min
    }

    public String getSujeitoDoToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token inexistente ou mal formatado!");
        }


        String token = authorizationHeader.substring(FilterJWT.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Token invalido ou expirado!");
        }
        return subject;
    }
}
