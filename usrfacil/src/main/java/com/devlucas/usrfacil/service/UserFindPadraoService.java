package com.devlucas.usrfacil.service;

import com.devlucas.usrfacil.exception.UserNaoExisteException;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFindPadraoService implements UserFindService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User userFindyByIdService(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNaoExisteException::new);
        return user;
    }
}
