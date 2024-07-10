package com.devlucas.usrfacil.auth;

import com.devlucas.usrfacil.dto.Avaliacao.AvaliacaoCompanyPostDto;
import com.devlucas.usrfacil.dto.Avaliacao.AvaliacaoProdutoPostDto;
import com.devlucas.usrfacil.dto.User.UserPostDto;
import com.devlucas.usrfacil.dto.auth.LoginDto;
import com.devlucas.usrfacil.model.*;
import com.devlucas.usrfacil.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Classe de testes do usuário")
public class AuthV1ControllerTests {
    final String URI_USUARIO = "/v1/users";
    final String URI_LOGIN = "/v1/auth";
    @Autowired
    MockMvc driver;

    ObjectMapper objectMapper = new ObjectMapper();
    ModelMapper modelMapper = new ModelMapper();

    @Nested
    class TestandoCRUD {
        @Autowired
        UserRepository userRepository;

        UserPostDto userPostDto;
        LoginDto loginDto;

        @BeforeEach
        void setup() {
            objectMapper.registerModule(new JavaTimeModule());
            userPostDto = UserPostDto.builder()
                    .chaveDeAcesso("2@3456")
                    .email("@s")
                    .name("pablo")
                    .cpf("3244222434")
                    .sexo("Masculino")
                    .profissao("Professor")
                    .build();
            loginDto = LoginDto.builder()
                    .email("@s")
                    .password("2@3456")
                    .build();
        }

        @AfterEach
        void tearDown() {
            userRepository.deleteAll();
        }

        @Test
        @DisplayName("Criando usuario")
        void testeAoCriarUsuario() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJSONString = driver.perform(post(URI_USUARIO + "/criar-cliente")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userPostDto)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            User user = objectMapper.readValue(responseJSONString, User.class);

            // Assert
            assertEquals("pablo", user.getName());
        }

        @Test
        @DisplayName("Realizando o login de umm usuário criado")
        void testeLoginUsuario() throws Exception {
            testeAoCriarUsuario();
            System.out.println(userRepository.findAll().size());
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJSONString = driver.perform(post(URI_LOGIN + "/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(loginDto)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            // Assert
            assertTrue(true, responseJSONString);
        }

    }
}