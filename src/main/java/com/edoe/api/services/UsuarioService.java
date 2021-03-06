package com.edoe.api.services;

import com.edoe.api.dto.UserLogin;
import com.edoe.api.entity.User;
import com.edoe.api.enums.Role;
import com.edoe.api.repositories.UserRepository;
import com.edoe.api.services.exceptions.NotCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean usuarioTemPermissao(String authorizationHeader, String email) throws NotCredentialException {
        String subject = serviceJWT.getSujeitoDoToken(authorizationHeader);
        Optional<User> optUsuario = userRepository.findByEmail(subject);
        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email)  ;
    }

    private boolean usuarioTemPermissaoAdmin(String authorizationHeader, String email) throws NotCredentialException {
        String subject = serviceJWT.getSujeitoDoToken(authorizationHeader);
        Optional<User> optUsuario = userRepository.findByEmail(subject);
        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email) && optUsuario.get().getRole() == Role.ADMIN ;
    }

    public boolean usuarioTemPermissaoDoador(String authorizationHeader, String email) throws NotCredentialException {
        String subject = serviceJWT.getSujeitoDoToken(authorizationHeader);
        Optional<User> optUsuario = userRepository.findByEmail(subject);
        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email) && optUsuario.get().getRole() == Role.APENAS_DOADOR ;
    }
    public boolean usuarioTemPermissaoReceptor(String authorizationHeader, String email) throws NotCredentialException {
        String subject = serviceJWT.getSujeitoDoToken(authorizationHeader);
        Optional<User> optUsuario = userRepository.findByEmail(subject);
        return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email) && optUsuario.get().getRole() == Role.APENAS_RECEPTOR ;
    }


    public User saveUsuario(String authorization ,User user) throws NotCredentialException {
        User userToken = UsuarioDoTokenItem(authorization);
        if(usuarioTemPermissaoAdmin(authorization,  UsuarioDoToken(authorization))){
            if(userToken.getRole() == Role.ADMIN){

            return userRepository.save(user);
            }
        }
        throw new NotCredentialException("Usuario n??o pode cadastrar");
    }

    public User updateRole(Long id, User userRole, String authorization) throws NotCredentialException {
        User user = userRepository.getById(id);

        User userToken = UsuarioDoTokenItem(authorization);
        if(usuarioTemPermissaoAdmin(authorization, UsuarioDoToken(authorization))){
            if(userToken.getRole() == Role.ADMIN){
            user.setRole(userRole.getRole());
            return userRepository.save(user);
            }
        }
        throw new NotCredentialException("Usuario n??o pode atualizar");
    }

    public String UsuarioDoToken(String authorization){
        String emailToken = serviceJWT.getSujeitoDoToken(authorization);
        User userToken = getUsuario(emailToken);
        return userToken.getEmail();
    }

    public User UsuarioDoTokenItem(String authorization){
        String emailToken = serviceJWT.getSujeitoDoToken(authorization);
        User userToken = getUsuario(emailToken);
        return userToken;
    }

    public User UserByID(Long id) {
        return userRepository.findById(id).get();
    }
}

