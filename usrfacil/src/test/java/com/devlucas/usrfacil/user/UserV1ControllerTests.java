package com.devlucas.usrfacil.user;

import com.devlucas.usrfacil.dto.UserPostDto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Classe de testes do usuário")
public class UserV1ControllerTests {
    final String URI_USUARIO = "/v1/users";
    @Autowired
    MockMvc driver;
    @Autowired
    UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    ModelMapper modelMapper = new ModelMapper();
    UserPostDto userPostDto;
    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        userPostDto = UserPostDto.builder()
                .email("@s")
                .name("pablo")
                .build();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }
    @Nested
    class TestandoCRUD {
        @Test
        @DisplayName("Criando usuario")
        void testeAoCriarUsuario() throws Exception {
            // Arrange
            // nenhuma necessidade além do setup()

            // Act
            String responseJSONString = driver.perform(post(URI_USUARIO)
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
        @DisplayName("Quando busco todos os usuários salvos")
        void testAoBuscarTodosUser() throws Exception {
            //Arrange
            UserPostDto userPostDto1 = UserPostDto.builder()
                    .name("ze")
                    .email("@sa")
                    .build();
            User user1 = modelMapper.map(userPostDto1, User.class);
            UserPostDto userPostDto2 = UserPostDto.builder()
                    .name("maria")
                    .email("@saw")
                    .build();
            User user2 = modelMapper.map(userPostDto1, User.class);
            userRepository.save(user1);
            userRepository.save(user2);
            //Act
            String responseJSONString = driver.perform(get(URI_USUARIO)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<User> usuarios = objectMapper.readValue(responseJSONString, new TypeReference<List<User>>(){});
            //Assert
            assertEquals(2, usuarios.size());
        }

        @Test
        @DisplayName("Quando busco um usuário pelo id")
        void testQuandoBuscoUmUsuario() throws Exception {
            UserPostDto userPostDto1 = UserPostDto.builder()
                    .name("ze")
                    .email("@sa")
                    .build();
            User user1 = modelMapper.map(userPostDto1, User.class);
            UserPostDto userPostDto2 = UserPostDto.builder()
                    .name("maria")
                    .email("@saw")
                    .build();
            User user2 = modelMapper.map(userPostDto1, User.class);
            User user = userRepository.save(user1);
            userRepository.save(user2);

            //Act
            String responseJSONString = driver.perform(get(URI_USUARIO + "/" + user.getID())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            User usuario = objectMapper.readValue(responseJSONString, User.class);
            //Assert
            assertEquals("ze", usuario.getName());
        }

        @Test
        @DisplayName("Quando deleto um usuário pelo id")
        void testQuandoDeletoUser() throws Exception{
            UserPostDto userPostDto1 = UserPostDto.builder()
                    .name("ze")
                    .email("@sa")
                    .build();
            User user1 = modelMapper.map(userPostDto1, User.class);
            UserPostDto userPostDto2 = UserPostDto.builder()
                    .name("maria")
                    .email("@saw")
                    .build();
            User user2 = modelMapper.map(userPostDto1, User.class);
            User user = userRepository.save(user1);
            userRepository.save(user2);
            assertEquals(2, userRepository.findAll().size());

            //Act
            String responseJSONString = driver.perform(delete(URI_USUARIO + "/" + user.getID())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            //Assert
            assertEquals(1, userRepository.findAll().size());
        }

    }
}
