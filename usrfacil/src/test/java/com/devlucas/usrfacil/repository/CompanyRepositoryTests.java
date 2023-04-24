package com.devlucas.usrfacil.repository;

import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Produto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@DisplayName("Testes para company repository")
public class CompanyRepositoryTests {
    @Autowired
    ProdutosRepository produtosRepository;

    Produto produto;
    Company company;
    @Autowired
    CompanyRepository driver;

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
        produtosRepository.save(produto);
        company.getCompanyProducts().add(produtosRepository.findAll().get(0));
        driver.save(company);
    }

    @Test
    @DisplayName("Acessando dados do produto")
    @Transactional                    // garante que os objetos estejam em um estado gerenciado pelo Hibernate
    void quandoAcessoAtributosDOProdutoDaEmpresa() {
        //Arrange
        //Act
        int qtdDeProdutosDaEmpresa = driver.findAll().get(0).getCompanyProducts().size();
        //Assert
        assertEquals(1, qtdDeProdutosDaEmpresa);
    }

    @Test
    @DisplayName("Acessando dados do produto")
    @Transactional                    // garante que os objetos estejam em um estado gerenciado pelo Hibernate
    void quandoAcessoAtributosDoProduto() {
        //Arrange
        //Act
        String nameProduto = driver.findAll().get(0).getCompanyProducts().get(0).getName();
        //Assert
        assertEquals(produto.getName(), nameProduto);
    }
}
