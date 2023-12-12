package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.dto.User.UserPostDto;
import com.devlucas.usrfacil.exception.UserNaoExisteException;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCrudPadraoService implements UserCrudService {
    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public User userCreateService(UserPostDto userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return userRepository.save(user);
    }

    @Override
    public void userDeleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> userFindAllService() {
        return userRepository.findAll();
    }

    @Override
    public User userFindyByIdService(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNaoExisteException::new);
        return user;
    }
}
