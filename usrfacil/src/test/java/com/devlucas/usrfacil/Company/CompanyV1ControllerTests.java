package com.devlucas.usrfacil.Company;

import com.devlucas.usrfacil.dto.Company.CompanyAtualizaValorProdutoDto;
import com.devlucas.usrfacil.dto.Company.CompanyPostDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Date;
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
                    .chaveDeAcesso("12345")
                    .name("Casas Bahia")
                    .cnpj("122133")
                    .email("casas@gmail.com")
                    .descricao("empresa voltada para o ramo de vendas.")
                    .telefones(new HashSet<>())
                    .companyProducts(new ArrayList<>())
                    .avCompany(new ArrayList<>())
                    .build();

            companyPostDto1 = CompanyPostDto.builder()
                    .chaveDeAcesso("23456")
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
            String responseJsonString = driver.perform(delete(URI_EMPRESA + "/" + company.getID())
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", company.getChaveDeAcesso()))
                    .andExpect(status().isNoContent())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            //Assert
            assertEquals(1, companyRepository.findAll().size());
        }
    }

    @Nested
    @Transactional
    @DisplayName("Classe de testes sobre atualizações básicas feitas pela company")
    public class TestAtualizacoes {
        @Autowired
        CompanyRepository companyRepository;
        @Autowired
        ProdutoRepository produtoRepository;
        @Autowired
        FabricanteRepository fabricanteRepository;
        @Autowired
        CategoriaRepository categoriaRepository;
        Fabricante fabricante;
        Categoria categoria;
        Company company1;
        Company company;
        Produto produto;
        @BeforeEach
        void setup() {
            objectMapper.registerModule(new JavaTimeModule());
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
            produto =  produtoRepository.save(Produto.builder()
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
                    .categoria(categoria)
                    .avaliacoesProduto(new ArrayList<>())
                    .build());
            company= Company.builder()
                    .chaveDeAcesso("12345")
                    .name("Casas Bahia")
                    .cnpj("122133")
                    .email("casas@gmail.com")
                    .descricao("empresa voltada para o ramo de vendas.")
                    .telefones(new HashSet<>())
                    .companyProducts(new ArrayList<>())
                    .build();

            company1 = Company.builder()
                    .chaveDeAcesso("12345")
                    .name("Magalu")
                    .cnpj("122133")
                    .email("magalu@gmail.com")
                    .descricao("empresa voltada para o ramo de vendas.")
                    .telefones(new HashSet<>())
                    .companyProducts(new ArrayList<>())
                    .build();
            company.getCompanyProducts().add(produto);
            company1.getCompanyProducts().add(produto);
            companyRepository.save(company);
            companyRepository.save(company1);
        }

        @AfterEach
        void tearDown() {
            companyRepository.deleteAll();
            categoriaRepository.deleteAll();
            fabricanteRepository.deleteAll();
            produtoRepository.deleteAll();
        }

        @Test
        @DisplayName("Alterando o valor de venda de um produto")
        void testalterandoValorDeVendaDoProduto() throws Exception {
            //Arrange
            CompanyAtualizaValorProdutoDto companyAtualizaValorProdutoDto = CompanyAtualizaValorProdutoDto.builder()
                    .valor(230.00)
                    .build();
            //Act
            String responseJSONSttring = driver.perform(patch(URI_EMPRESA + "/" + company.getID() + "/modifica-valor-produto/" + produto.getID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(companyAtualizaValorProdutoDto))
                    .param("codigoAcesso", company.getChaveDeAcesso()))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJSONSttring, Produto.class);
            //Assert
            assertEquals(230.00, resultado.getPreco_venda());
            assertEquals(230.00, company.getCompanyProducts().get(0).getPreco_venda());
        }


    }

    @Nested
    @Transactional
    @DisplayName("Classe de testes sobre adição de objetos a empresa")
    public class TestAdicaoObjetos {
        @Autowired
        CompanyRepository companyRepository;
        @Autowired
        ProdutoRepository produtoRepository;
        @Autowired
        FabricanteRepository fabricanteRepository;
        @Autowired
        CategoriaRepository categoriaRepository;
        Fabricante fabricante;
        Categoria categoria;
        Company company1;
        Company company;
        Produto produto;
        @BeforeEach
        void setup() {
            objectMapper.registerModule(new JavaTimeModule());
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
            company= companyRepository.save(Company.builder()
                    .chaveDeAcesso("12345")
                    .name("Casas Bahia")
                    .cnpj("122133")
                    .email("casas@gmail.com")
                    .descricao("empresa voltada para o ramo de vendas.")
                    .telefones(new HashSet<>())
                    .companyProducts(new ArrayList<>())
                    .build());

            company1 = companyRepository.save(Company.builder()
                    .chaveDeAcesso("12345")
                    .name("Magalu")
                    .cnpj("122133")
                    .email("magalu@gmail.com")
                    .descricao("empresa voltada para o ramo de vendas.")
                    .telefones(new HashSet<>())
                    .companyProducts(new ArrayList<>())
                    .build());
            produto =  produtoRepository.save(Produto.builder()
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
                    .categoria(categoria)
                    .avaliacoesProduto(new ArrayList<>())
                    .build());
        }

        @AfterEach
        void tearDown() {
            companyRepository.deleteAll();
            categoriaRepository.deleteAll();
            fabricanteRepository.deleteAll();
            produtoRepository.deleteAll();
        }

        @Test
        @DisplayName("Adicionando produto a company")
        void testAdicionaProdutoCompany() throws Exception {
            //Arrange
            //Act
            String responseJSONString = driver.perform(post(URI_EMPRESA + "/" + produto.getID() + "/adicionar-produto")
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("codigoAcesso", produto.getCompany().getChaveDeAcesso()))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            //Assert
            assertEquals(1, company.getCompanyProducts().size());
        }
    }
}
