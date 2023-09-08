package com.devlucas.usrfacil.controller;

import com.devlucas.usrfacil.dto.UserPostDto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.service.UserCRUDService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // configurando a classe para ser um controller e ela vai responder por requesições.
@RequestMapping(value = "/v1/users")
public class UserController {
    @Autowired
    private UserCRUDService userCRUDService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userCRUDService.userFindAllService());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserPostDto userPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userCRUDService.userCreateService(userPostDto));
    }
}
