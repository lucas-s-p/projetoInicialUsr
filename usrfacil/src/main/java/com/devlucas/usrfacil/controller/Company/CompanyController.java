package com.devlucas.usrfacil.controller.Company;

import com.devlucas.usrfacil.dto.Company.CompanyPostDto;
import com.devlucas.usrfacil.dto.User.UserPostDto;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.service.Company.CompanyCrudService;
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
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
        this.companyCrudService.companyDelete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

}
