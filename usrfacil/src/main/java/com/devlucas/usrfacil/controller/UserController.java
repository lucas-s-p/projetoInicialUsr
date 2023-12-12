package com.devlucas.usrfacil.controller;

import com.devlucas.usrfacil.dto.User.UserPostDto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.service.User.UserCrudService;
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
    private UserCrudService userCrudService;


    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userCrudService.userFindAllService());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userCrudService.userFindyByIdService(id));
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserPostDto userPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userCrudService.userCreateService(userPostDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        this.userCrudService.userDeleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

}
