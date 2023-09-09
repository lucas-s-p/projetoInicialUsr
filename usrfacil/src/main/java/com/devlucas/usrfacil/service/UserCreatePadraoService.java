package com.devlucas.usrfacil.service;

import com.devlucas.usrfacil.dto.UserPostDto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreatePadraoService implements UserCreateService {
    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public User userCreateService(UserPostDto userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }
}
