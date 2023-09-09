package com.devlucas.usrfacil.service;

import com.devlucas.usrfacil.dto.UserPostDto;
import com.devlucas.usrfacil.model.User;

import java.util.List;

@FunctionalInterface
public interface UserCreateService {
    User userCreateService(UserPostDto userDTO);
}
