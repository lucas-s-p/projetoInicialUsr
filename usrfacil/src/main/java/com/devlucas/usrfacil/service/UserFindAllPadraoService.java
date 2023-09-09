package com.devlucas.usrfacil.service;

import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserFindAllPadraoService  implements UserFindAllService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> userFindAllService() {
        return userRepository.findAll();
    }
}
