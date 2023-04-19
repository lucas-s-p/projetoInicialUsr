package com.devlucas.usrfacil.controller;

import com.devlucas.usrfacil.repository.UserRepository;
import com.devlucas.usrfacil.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  // configurando a classe para ser um controller e ela vai responder por requesições.
@RequestMapping(value = "/users")
public class UserController {
    @Autowired // injecão de dependência, não precisa instanciar.
    private UserRepository userRepository;
    @GetMapping  // usa para informar quando fizer a requisição web. Poderia ser post, put, ...
    public List<User> findAll() {
        List<User> result = userRepository.findAll();
        return result;
    }
}
