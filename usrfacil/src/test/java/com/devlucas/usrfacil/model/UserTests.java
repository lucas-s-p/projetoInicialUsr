package com.devlucas.usrfacil.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Classe de testes do usuário")
public class UserTests {
    Company company;
    User user;

    @BeforeEach
    void setup() {
        company = Company.builder()
                .ID(1L)
                .name("Depart. Informatica")
                .build();

        user = User.builder()
                .ID(1L)
                .name("Lucas")
                .email("lucas.per.@or")
                .build();
    }

    @Test
    @DisplayName("Teste verificação de um id do usuario")
    void testPegaIdDoUsuario() {
        //Arrange
        Long id = user.getID();
        //Act
        //Assert
        assertEquals(user.getID(), id);
    }
}
