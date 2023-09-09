package com.devlucas.usrfacil.controller;

import com.devlucas.usrfacil.dto.UserPostDto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.service.UserCreateService;
import com.devlucas.usrfacil.service.UserDeleteService;
import com.devlucas.usrfacil.service.UserFindAllService;
import com.devlucas.usrfacil.service.UserFindService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // configurando a classe para ser um controller e ela vai responder por requesições.
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
//@Controller
public class UserController {
    @Autowired
    private UserFindAllService userFindAllService;
    @Autowired
    private UserCreateService userCreateService;
    @Autowired
    private UserDeleteService userDeleteService;
    @Autowired
    private UserFindService userFindService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userFindAllService.userFindAllService());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userFindService.userFindyByIdService(id));
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserPostDto userPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userCreateService.userCreateService(userPostDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        this.userDeleteService.userDeleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

}
