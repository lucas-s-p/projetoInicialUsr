package com.devlucas.usrfacil.service;

import com.devlucas.usrfacil.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@FunctionalInterface
public interface UserFindAllService {
    List<User> userFindAllService();
}
