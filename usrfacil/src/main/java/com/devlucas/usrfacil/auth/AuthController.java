package com.devlucas.usrfacil.auth;

import com.devlucas.usrfacil.dto.auth.LoginDto;
import com.devlucas.usrfacil.service.auth.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private UserLoginService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        boolean token = userService.authenticate(loginDto);
        if (token) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
