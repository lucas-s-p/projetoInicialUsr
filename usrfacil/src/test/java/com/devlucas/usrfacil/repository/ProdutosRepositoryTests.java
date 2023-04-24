package com.devlucas.usrfacil.repository;

import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DisplayName("Testes para a classe produtos usando Jpa")
public class ProdutosRepositoryTests {
    @Autowired
    ProdutosRepository driver;

    Produto produto;
    Company company;
    @Autowired
    CompanyRepository companyRepository;

    @BeforeEach
    void setup() {
        company = Company.builder()
                .ID(1L)
                .name("Empresa. Informatica")
                .companyProducts(new ArrayList<>())
                .build();

        produto = Produto.builder()
                .ID(1L)
                //.company(company)  //Não precisa passar ? já que um produto pode tá em várias empresas
                .name("Smartphone Galax S10")
                .preco(1020.00)
                .codigoDeBarras("7896363326")
                .fabriante("Samsung")
                .build();
        driver.save(produto);
        company.getCompanyProducts().add(driver.findAll().get(0));
        companyRepository.save(company);
    }

    @Test
    @DisplayName("Acessando dados do produto")
    void quandoAcessoAtributosDoProduto() {
        //Arrange
        //Act
        Optional<Produto> produtoOptional =  driver.findById(1L);
        //Assert
        assertTrue(produtoOptional.isPresent());
        assertEquals(produto.getName(), produtoOptional.get().getName());
    }


}
