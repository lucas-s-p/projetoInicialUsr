package com.devlucas.usrfacil.Company;

import com.devlucas.usrfacil.dto.Company.CompanyPostDto;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.repository.CompanyRepository;
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
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Classe de Testes sobre Empresa")
public class CompanyV1ControllerTests {
    final String URI_EMPRESA = "/v1/company";

    @Autowired
    MockMvc driver;
    ObjectMapper objectMapper = new ObjectMapper();

    ModelMapper modelMapper =  new ModelMapper();
    @Autowired
    CompanyRepository companyRepository;
    @Nested
    public class TestCrud {

        CompanyPostDto companyPostDto;
        CompanyPostDto companyPostDto1;
        Company company1;
        @BeforeEach
        void setup() {
            objectMapper.registerModule(new JavaTimeModule());
            companyPostDto =   CompanyPostDto.builder()
                    .name("Casas Bahia")
                    .cnpj("122133")
                    .email("casas@gmail.com")
                    .descricao("empresa voltada para o ramo de vendas.")
                    .telefones(new HashSet<>())
                    .companyProducts(new ArrayList<>())
                    .avCompany(new ArrayList<>())
                    .build();

            companyPostDto1 = CompanyPostDto.builder()
                    .name("Magalu")
                    .cnpj("122133")
                    .email("magalu@gmail.com")
                    .descricao("empresa voltada para o ramo de vendas.")
                    .telefones(new HashSet<>())
                    .companyProducts(new ArrayList<>())
                    .avCompany(new ArrayList<>())
                    .build();
            company1 =  companyRepository.save(modelMapper.map(companyPostDto1, Company.class));
        }

        @AfterEach
        void tearDown() {
            companyRepository.deleteAll();
        }
        @Test
        @DisplayName("Criando uma empresa com dados válidos")
        void testCriandoEmpresaComDadosValidos() throws Exception {
            //Arrange
            //Act
            String responseJSONString = driver.perform(post(URI_EMPRESA)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(companyPostDto)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Company resultado = objectMapper.readValue(responseJSONString, Company.class);
            //Assert
            assertEquals("Casas Bahia", resultado.getName());

        }

        @Test
        @DisplayName("Quando busco uma empresa com dados inválidos")
        void testQuandoBuscoUmaEmpresaComDadosValidos() throws Exception {
            //Arrange
            Company company = companyRepository.save(modelMapper.map(companyPostDto, Company.class));
            //Act
            String responseJSONString = driver.perform(get(URI_EMPRESA + "/" + company1.getID())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Company resultado = objectMapper.readValue(responseJSONString, Company.class);
            //Assert
            assertEquals("Magalu", resultado.getName());
        }

        @Test
        @DisplayName("Quando busco por todas empresas com dados válidos")
        void  testQuandoBuscoPorTodasEmpresascomDadosvalidos() throws Exception{
            //Arrange
            testQuandoBuscoUmaEmpresaComDadosValidos();
            //Act
            String responseJSONString = driver.perform(get(URI_EMPRESA)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Company> resultado = objectMapper.readValue(responseJSONString, new TypeReference<List<Company>>(){});
            //Assert

            assertEquals(2, resultado.size());
        }

        @Test
        @DisplayName("Quando eu deleto uma empresa com dados válidos")
        void testQuandoDeletoUmaEmpresaComDadosValidos() throws Exception {
            //Arrange
            Company company = companyRepository.save(modelMapper.map(companyPostDto, Company.class));
            //Act
            String responseJSONString = driver.perform(delete(URI_EMPRESA + "/" + company.getID())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            //Assert
            assertEquals(1, companyRepository.findAll().size());
        }
    }
}
