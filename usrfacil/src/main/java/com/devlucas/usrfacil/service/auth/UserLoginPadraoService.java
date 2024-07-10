package com.devlucas.usrfacil.service.auth;

import com.devlucas.usrfacil.dto.auth.LoginDto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserLoginPadraoService implements UserLoginService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean authenticate(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user != null && user.getChaveDeAcesso().equals(loginDto.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
