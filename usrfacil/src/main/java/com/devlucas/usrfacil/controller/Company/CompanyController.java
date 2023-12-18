package com.devlucas.usrfacil.controller.Company;

import com.devlucas.usrfacil.dto.Company.CompanyAtualizaValorProdutoDto;
import com.devlucas.usrfacil.dto.Company.CompanyPostDto;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.service.Company.CompanyAdicionaObjetosService;
import com.devlucas.usrfacil.service.Company.CompanyCrudService;
import com.devlucas.usrfacil.service.Company.ModificaValorProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/company", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {
    @Autowired
    private CompanyCrudService companyCrudService;

    @Autowired
    private CompanyAdicionaObjetosService companyAdicionaObjetosService;

    @Autowired
    private ModificaValorProdutoService modificaValorProdutoService;

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody @Valid CompanyPostDto companyPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyCrudService.companyCreate(companyPostDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCompany(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyCrudService.companyFindById(id));
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAllCompany() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyCrudService.companyFindAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id,
            @RequestParam String codigoAcesso) {
        this.companyCrudService.companyDelete(id, codigoAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

    @PatchMapping("/{id}/modifica-valor-produto/{idProduto}")
    public ResponseEntity<?> modificaValorDoProduto(
            @RequestBody @Valid CompanyAtualizaValorProdutoDto companyAtualizaValorProdutoDto,
            @PathVariable Long id,
            @PathVariable  Long idProduto,
            @RequestParam String codigoAcesso
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(modificaValorProdutoService.modificaValorProduto(companyAtualizaValorProdutoDto, id, idProduto, codigoAcesso));
    }

    @PostMapping("/{idProduto}/adicionar-produto")
    public ResponseEntity<?> adicionarProduto(
            @PathVariable Long idProduto,
            @RequestParam String codigoAcesso
            ) {
        this.companyAdicionaObjetosService.adicionaProduto(idProduto, codigoAcesso);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("");
    }
}
