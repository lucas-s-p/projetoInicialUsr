package com.devlucas.usrfacil.service;

import com.devlucas.usrfacil.dto.UserPostDto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserCRUDPadraoService implements UserCRUDService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public User userCreateService(UserPostDto userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }

    @Override
    public User userFindyByIdService(Long id) {
        return null;
    }

    @Override
    public List<User> userFindAllService() {
        return userRepository.findAll();
    }

    @Override
    public void userDeleteById(Long id) {

    }
}
