package com.devlucas.usrfacil.service;

import com.devlucas.usrfacil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeletePadraoService implements UserDeleteService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void userDeleteById(Long id) {
        userRepository.deleteById(id);
    }
}
