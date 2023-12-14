package com.devlucas.usrfacil.Categoria;

import com.devlucas.usrfacil.dto.Categoria.CategoriaPostDto;
import com.devlucas.usrfacil.model.Categoria;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.repository.CategoriaRepository;
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
@DisplayName("Classe de Testes sobre Categoria")
public class CategoriaV1ControllerTests {
    final String URI_CATEGORIA = "/v1/categoria";

    @Autowired
    MockMvc driver;
    ObjectMapper objectMapper = new ObjectMapper();

    ModelMapper modelMapper =  new ModelMapper();
    @Autowired
    CategoriaRepository categoriaRepository;
    @Nested
    public class TestCrud {

        CategoriaPostDto categoriaPostDto;
        CategoriaPostDto categoriaPostDto1;
        Categoria categoria1;
        @BeforeEach
        void setup() {
            objectMapper.registerModule(new JavaTimeModule());
            categoriaPostDto =   CategoriaPostDto.builder()
                    .nome("Limpeza")
                    .build();

            categoriaPostDto1 = CategoriaPostDto.builder()
                    .nome("Varejos")
                    .build();
            categoria1 =  categoriaRepository.save(modelMapper.map(categoriaPostDto1, Categoria.class));
        }

        @AfterEach
        void tearDown() {
            categoriaRepository.deleteAll();
        }
        @Test
        @DisplayName("Criando uma categoria com dados válidos")
        void testCriandoCategoriaComDadosValidos() throws Exception {
            //Arrange
            //Act
            String responseJSONString = driver.perform(post(URI_CATEGORIA)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(categoriaPostDto)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Categoria resultado = objectMapper.readValue(responseJSONString, Categoria.class);
            //Assert
            assertEquals("Limpeza", resultado.getNome());

        }

        @Test
        @DisplayName("Quando busco uma categoria com dados inválidos")
        void testQuandoBuscoUmaCategoriaComDadosValidos() throws Exception {
            //Arrange
            Categoria categoria = categoriaRepository.save(modelMapper.map(categoriaPostDto, Categoria.class));
            //Act
            String responseJSONString = driver.perform(get(URI_CATEGORIA + "/" + categoria1.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Categoria resultado = objectMapper.readValue(responseJSONString, Categoria.class);
            //Assert
            assertEquals("Varejos", resultado.getNome());
        }

        @Test
        @DisplayName("Quando busco por todas categorias com dados válidos")
        void  testQuandoBuscoPorTodasCatgeoriascomDadosvalidos() throws Exception{
            //Arrange
            testQuandoBuscoUmaCategoriaComDadosValidos();
            //Act
            String responseJSONString = driver.perform(get(URI_CATEGORIA)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Categoria> resultado = objectMapper.readValue(responseJSONString, new TypeReference<List<Categoria>>(){});
            //Assert

            assertEquals(2, resultado.size());
        }

        @Test
        @DisplayName("Quando eu deleto uma categoria com dados válidos")
        void testQuandoDeletoUmaCategoriaComDadosValidos() throws Exception {
            //Arrange
            Categoria categoria = categoriaRepository.save(modelMapper.map(categoriaPostDto, Categoria.class));
            //Act
            String responseJSONString = driver.perform(delete(URI_CATEGORIA + "/" + categoria.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            //Assert
            assertEquals(1, categoriaRepository.findAll().size());
        }
    }
}
