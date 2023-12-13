package com.devlucas.usrfacil.controller.Categoria;

import com.devlucas.usrfacil.dto.Categoria.CategoriaPostDto;
import com.devlucas.usrfacil.dto.User.UserPostDto;
import com.devlucas.usrfacil.model.Categoria;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.service.Categoria.CategoriaCrudService;
import com.devlucas.usrfacil.service.User.UserCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
//@Controller
public class CategoriaController {
    @Autowired
    private CategoriaCrudService categoriaCrudService;


    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoriaCrudService.categoriaFindAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoriaCrudService.categoriaFindById(id));
    }
    @PostMapping
    public ResponseEntity<?> createCategoria(@RequestBody @Valid CategoriaPostDto categoriaPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaCrudService.categoriaCreate(categoriaPostDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        this.categoriaCrudService.categoriaDelete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

}
