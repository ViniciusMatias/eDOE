package com.edoe.api.services;

import com.edoe.api.dto.UserLogin;
import com.edoe.api.entity.User;
import com.edoe.api.enums.Role;
import com.edoe.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.Optional;
@Service
public class UsuarioService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceJWT serviceJWT;

    public User getUsuario(String email) {
        Optional<User> optUsuario = userRepository.findByEmail(email);
        if (optUsuario.isEmpty())
            throw new IllegalArgumentException();
        return optUsuario.get();
    }

    public boolean validaUsuarioSenha(UserLogin user) {
        Optional<User> optUsuario = userRepository.findByEmail(user.getEmail());
        if (optUsuario.isPresent() && optUsuario.get().getPassword().equals(user.getPassword()))
            return true;
        return false;
    }

    private boolean usuarioTemPermissaoAdmin(String authorizationHeader, String email) throws ServletException {
        String subject = serviceJWT.getSujeitoDoToken(authorizationHeader);
        Optional<User> optUsuario = userRepository.findByEmail(subject);
        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email) && optUsuario.get().getRole() == Role.ADMIN ;
    }

    public User saveUsuario(String authorization ,User user) throws ServletException {

        if(usuarioTemPermissaoAdmin(authorization,  UsuarioDoToken(authorization))){
            return userRepository.save(user);
        }
        throw new ServletException("Usuario não pode cadastrar");
    }

    public User updateRole(Long id, User userRole, String authorization) throws ServletException {
        User user = userRepository.getById(id);
        user.setRole(userRole.getRole());
        if(usuarioTemPermissaoAdmin(authorization, UsuarioDoToken(authorization))){
            return userRepository.save(user);
        }
        throw new ServletException("Usuario não pode atualizar");
    }

    public String UsuarioDoToken(String authorization){
        String emailToken = serviceJWT.getSujeitoDoToken(authorization);
        User userToken = getUsuario(emailToken);
        return userToken.getEmail();
    }
}
