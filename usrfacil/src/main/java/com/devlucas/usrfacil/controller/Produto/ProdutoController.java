package com.devlucas.usrfacil.controller.Produto;

import com.devlucas.usrfacil.dto.Produto.ProdutoPostDto;
import com.devlucas.usrfacil.dto.User.UserPostDto;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.service.Produto.ProdutoCrudService;
import com.devlucas.usrfacil.service.User.UserCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // configurando a classe para ser um controller e ela vai responder por requesições.
@RequestMapping(value = "/v1/produto", produces = MediaType.APPLICATION_JSON_VALUE)
//@Controller
public class ProdutoController {
    @Autowired
    private ProdutoCrudService produtoCrudService;

    @PostMapping
    public ResponseEntity<?> createProduto(
            @RequestBody @Valid ProdutoPostDto produtoPostDto,
            @RequestParam Long companyId,
            @RequestParam Long fabricanteId
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoCrudService.produtoCreate(produtoPostDto, companyId, fabricanteId));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoCrudService.produtoFindAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoCrudService.produtoFindById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
        this.produtoCrudService.produtoDelete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

}
