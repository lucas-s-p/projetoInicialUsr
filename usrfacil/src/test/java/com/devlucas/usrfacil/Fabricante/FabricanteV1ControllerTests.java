package com.devlucas.usrfacil.Fabricante;

import com.devlucas.usrfacil.dto.Fabricante.FabricantePostDto;
import com.devlucas.usrfacil.model.Fabricante;
import com.devlucas.usrfacil.repository.FabricanteRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Classe de Testes sobre Fabricante")
public class FabricanteV1ControllerTests {
    final String URI_FABRICANTE = "/v1/fabricante";

    @Autowired
    MockMvc driver;
    ObjectMapper objectMapper = new ObjectMapper();

    ModelMapper modelMapper =  new ModelMapper();
    @Autowired
    FabricanteRepository fabricanteRepository;
    @Nested
    public class TestCrud {

        FabricantePostDto fabricantePostDto;
        FabricantePostDto fabricantePostDto1;
        Fabricante fabricante1;
        @BeforeEach
        void setup() {
            objectMapper.registerModule(new JavaTimeModule());
            fabricantePostDto =   FabricantePostDto.builder()
                    .nome("LucasVendas")
                    .cnpj("324542")
                    .descricao("empresa voltada para produção.")
                    .email("lucasvendas@gmail.com")
                    .telefones(new ArrayList<>())
                    .build();

            fabricantePostDto1 = FabricantePostDto.builder()
                    .nome("JoaoVendas")
                    .cnpj("32454222")
                    .descricao("empresa voltada para produção.")
                    .email("joaovendas@gmail.com")
                    .telefones(new ArrayList<>())
                    .build();
            fabricante1 =  fabricanteRepository.save(modelMapper.map(fabricantePostDto1, Fabricante.class));
        }

        @AfterEach
        void tearDown() {
            fabricanteRepository.deleteAll();
        }
        @Test
        @DisplayName("Criando um fabricante com dados válidos")
        void testCriandoFabriccanteComDadosValidos() throws Exception {
            //Arrange
            //Act
            String responseJSONString = driver.perform(post(URI_FABRICANTE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(fabricantePostDto)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Fabricante resultado = objectMapper.readValue(responseJSONString, Fabricante.class);
            //Assert
            assertEquals("LucasVendas", resultado.getNome());

        }

        @Test
        @DisplayName("Quando busco um fabricante com dados inválidos")
        void testQuandoBuscoUmFabricanteComDadosValidos() throws Exception {
            //Arrange
            Fabricante fabricante = fabricanteRepository.save(modelMapper.map(fabricantePostDto, Fabricante.class));
            //Act
            String responseJSONString = driver.perform(get(URI_FABRICANTE + "/" + fabricante1.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Fabricante resultado = objectMapper.readValue(responseJSONString, Fabricante.class);
            //Assert
            assertEquals("JoaoVendas", resultado.getNome());
        }

        @Test
        @DisplayName("Quando busco por todos fabricantes com dados válidos")
        void  testQuandoBuscoPorTodosFabricantescomDadosvalidos() throws Exception{
            //Arrange
            testQuandoBuscoUmFabricanteComDadosValidos();
            //Act
            String responseJSONString = driver.perform(get(URI_FABRICANTE)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Fabricante> resultado = objectMapper.readValue(responseJSONString, new TypeReference<List<Fabricante>>(){});
            //Assert

            assertEquals(2, resultado.size());
        }

        @Test
        @DisplayName("Quando eu deleto um fabricante com dados válidos")
        void testQuandoDeletoUmFabricanteComDadosValidos() throws Exception {
            //Arrange
            Fabricante fabricante = fabricanteRepository.save(modelMapper.map(fabricantePostDto, Fabricante.class));
            //Act
            String responseJSONString = driver.perform(delete(URI_FABRICANTE + "/" + fabricante.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            //Assert
            assertEquals(1, fabricanteRepository.findAll().size());
        }
    }
}

