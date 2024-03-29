package com.devlucas.usrfacil.controller.Fabricante;

import com.devlucas.usrfacil.dto.Fabricante.FabricantePostDto;
import com.devlucas.usrfacil.dto.User.UserPostDto;
import com.devlucas.usrfacil.model.Fabricante;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.service.Fabricante.FabricanteCrudService;
import com.devlucas.usrfacil.service.User.UserCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // configurando a classe para ser um controller e ela vai responder por requesições.
@RequestMapping(value = "/v1/fabricante", produces = MediaType.APPLICATION_JSON_VALUE)
//@Controller
public class FabricanteController {
    @Autowired
    private FabricanteCrudService fabricanteCrudService;


    @GetMapping
    public ResponseEntity<List<Fabricante>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fabricanteCrudService.fabricanteFindAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fabricanteCrudService.fabricanteFindById(id));
    }
    @PostMapping
    public ResponseEntity<?> createFabricante(@RequestBody @Valid FabricantePostDto fabricantePostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fabricanteCrudService.fabricanteCreate(fabricantePostDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFabricante(@PathVariable Long id) {
        this.fabricanteCrudService.fabricanteDelete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

}

