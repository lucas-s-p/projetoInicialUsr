package com.devlucas.usrfacil.service;

import com.devlucas.usrfacil.dto.UserPostDto;
import com.devlucas.usrfacil.model.User;

import java.util.List;


public interface UserCRUDService {
    User userCreateService(UserPostDto userDTO);
    User userFindyByIdService(Long id);
    List<User> userFindAllService();
    void userDeleteById(Long id);
}
