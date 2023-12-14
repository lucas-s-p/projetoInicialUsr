package com.devlucas.usrfacil.Produto;

import com.devlucas.usrfacil.dto.Categoria.CategoriaPostDto;
import com.devlucas.usrfacil.dto.Produto.ProdutoPostDto;
import com.devlucas.usrfacil.model.Categoria;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Fabricante;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.repository.CategoriaRepository;
import com.devlucas.usrfacil.repository.CompanyRepository;
import com.devlucas.usrfacil.repository.FabricanteRepository;
import com.devlucas.usrfacil.repository.ProdutoRepository;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Classe de Testes sobre Produto")
@Transactional
public class ProdutoV1ControllerTests {
    final String URI_PRODUTO = "/v1/produto";

    @Autowired
    MockMvc driver;
    ObjectMapper objectMapper = new ObjectMapper();

    ModelMapper modelMapper =  new ModelMapper();
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FabricanteRepository fabricanteRepository;

    @Autowired
    CategoriaRepository categoriaRepository;
    @Nested
    public class TestCrud {

        ProdutoPostDto produtoPostDto;
        ProdutoPostDto produtoPostDto1;
        Produto produto1;
        Company company;
        Fabricante fabricante;
        Categoria categoria;

        @BeforeEach
        void setup() {
            objectMapper.registerModule(new JavaTimeModule());
            company = companyRepository.save(Company.builder()
                    .name("Casas Bahia")
                    .cnpj("122133")
                    .email("casas@gmail.com")
                    .descricao("empresa voltada para o ramo de vendas.")
                    .telefones(new ArrayList<>())
                    .companyProducts(new ArrayList<>())
                    .build());
            fabricante = fabricanteRepository.save(Fabricante.builder()
                    .nome("LucasVendas")
                    .cnpj("324542")
                    .descricao("empresa voltada para produção.")
                    .email("lucasvendas@gmail.com")
                    .telefones(new ArrayList<>())
                    .build());

            categoria = categoriaRepository.save(Categoria.builder()
                    .nome("Varejos")
                    .produto(new ArrayList<>())
                    .build());
            produtoPostDto =   ProdutoPostDto.builder()
                    .name("Cadeira")
                    .company(company)
                    .codigoDeBarras("23456789")
                    .dataFabricação(new Date("12/03/2023"))
                    .dataValidade(new Date("12/01/2024"))
                    .fabriante(fabricante)
                    .preco_compra(23.00)
                    .preco_venda(30.00)
                    .descricao("Produto produzido ecologicamente.")
                    .quantidade(123)
                    .build();

            produtoPostDto1 = ProdutoPostDto.builder()
                    .name("Mesa")
                    .company(company)
                    .codigoDeBarras("23456784")
                    .dataFabricação(new Date("12/03/2020"))
                    .dataValidade(new Date("12/01/2024"))
                    .fabriante(fabricante)
                    .preco_compra(223.00)
                    .preco_venda(380.00)
                    .descricao("Produto produzido ecologicamente.")
                    .quantidade(10)
                    .build();

            produto1 =  produtoRepository.save(modelMapper.map(produtoPostDto1, Produto.class));
        }

        @AfterEach
        void tearDown() {
            produtoRepository.deleteAll();
        }
        @Test
        @DisplayName("Criando um produto com dados válidos")
        void testCriandoProdutoComDadosValidos() throws Exception {
            //Arrange
            //Act
            String responseJSONString = driver.perform(post(URI_PRODUTO)
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("companyId", company.getID().toString())
                            .param("fabricanteId", fabricante.getId().toString())
                            .param("categoriaId", categoria.getId().toString())
                            .content(objectMapper.writeValueAsString(produtoPostDto)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJSONString, Produto.class);
            //Assert
            assertEquals("Cadeira", resultado.getName());
        }

        @Test
        @DisplayName("Quando busco um produto com dados inválidos")
        void testQuandoBuscoUmProdutoComDadosValidos() throws Exception {
            //Arrange
            Produto produto = produtoRepository.save(modelMapper.map(produtoPostDto, Produto.class));
            //Act
            String responseJSONString = driver.perform(get(URI_PRODUTO + "/" + produto1.getID())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJSONString, Produto.class);
            //Assert
            assertEquals("Mesa", resultado.getName());
        }

        @Test
        @DisplayName("Quando busco por todos produtos com dados válidos")
        void  testQuandoBuscoPorTodosProdutoscomDadosvalidos() throws Exception{
            //Arrange
            testQuandoBuscoUmProdutoComDadosValidos();
            //Act
            String responseJSONString = driver.perform(get(URI_PRODUTO)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            List<Produto> resultado = objectMapper.readValue(responseJSONString, new TypeReference<List<Produto>>(){});
            //Assert

            assertEquals(2, resultado.size());
        }

        @Test
        @DisplayName("Quando eu deleto um produto com dados válidos")
        void testQuandoDeletoUmProdutoComDadosValidos() throws Exception {
            //Arrange
            Produto produto = produtoRepository.save(modelMapper.map(produtoPostDto, Produto.class));
            //Act
            String responseJSONString = driver.perform(delete(URI_PRODUTO + "/" + produto.getID())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            //Assert
            assertEquals(1, produtoRepository.findAll().size());
        }
    }
}

