package com.devlucas.usrfacil.service;

import com.devlucas.usrfacil.model.User;
@FunctionalInterface
public interface UserFindService {
        User userFindyByIdService(Long id);
}
