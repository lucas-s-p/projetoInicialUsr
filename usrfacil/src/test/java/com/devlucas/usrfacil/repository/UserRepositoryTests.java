package com.devlucas.usrfacil.repository;

import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Testes de usuario usando como repositorio a JPA")
public class UserRepositoryTests {
    User user;
    @Autowired
    UserRepository driver;

    @BeforeEach
    void setup() {

        user = User.builder()   // Ordem de criação importa para o teste. Preciso ter o user e depois o departament
                .ID(1L)
                .name("Lucas")
                .email("lucas.per.@or")
                .build();


        driver.save(user);
    }

    @Test
    @DisplayName("Teste verificação de um usuário")
    void testPegaOAtributosDoUser() {
        //Arrange
        String nameUser = driver.findAll().get(0).getName();
        //Act
        //Assert
        assertEquals(user.getName(), nameUser);
    }

    @Test
    @DisplayName("Teste verificação de um departamento de um usuário")
    void testPegaAtributosDoUsuarioNoBD() {
        //Arrange
        String nameDepartament = driver.findAll().get(0).getName();
        //Act
        //Assert
        assertEquals(user.getName(), nameDepartament);
    }

}
