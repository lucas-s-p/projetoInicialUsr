package com.devlucas.usrfacil.service.auth;

import com.devlucas.usrfacil.dto.auth.LoginDto;

@FunctionalInterface
public interface UserLoginService {
    boolean authenticate(LoginDto loginDto);
}
