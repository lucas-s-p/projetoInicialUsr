package com.devlucas.usrfacil.User;

import com.devlucas.usrfacil.dto.Avaliacao.AvaliacaoCompanyPostDto;
import com.devlucas.usrfacil.dto.Avaliacao.AvaliacaoProdutoPostDto;
import com.devlucas.usrfacil.dto.Produto.ProdutoPostDto;
import com.devlucas.usrfacil.dto.User.UserPostDto;
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

    ObjectMapper objectMapper = new ObjectMapper();
    ModelMapper modelMapper = new ModelMapper();

    @Nested
    class TestandoCRUD {
        @Autowired
        UserRepository userRepository;
        UserPostDto userPostDto;
        @BeforeEach
        void setup() {
            objectMapper.registerModule(new JavaTimeModule());
            userPostDto = UserPostDto.builder()
                    .email("@s")
                    .name("pablo")
                    .cpf("3244222434")
                    .sexo("Masculino")
                    .profissao("Professor")
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
                    .cpf("3244222434")
                    .sexo("Não binário")
                    .profissao("Professor")
                    .build();
            User user1 = modelMapper.map(userPostDto1, User.class);
            UserPostDto userPostDto2 = UserPostDto.builder()
                    .name("maria")
                    .email("@saw")
                    .cpf("3244222434")
                    .sexo("Feminino")
                    .profissao("Professora")
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
                    .cpf("32442267734")
                    .sexo("Masculino")
                    .profissao("Professor")
                    .build();
            User user1 = modelMapper.map(userPostDto1, User.class);
            UserPostDto userPostDto2 = UserPostDto.builder()
                    .name("maria")
                    .email("@saw")
                    .cpf("3244222434")
                    .sexo("Feminino")
                    .profissao("Professora")
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
                    .cpf("3244222434")
                    .sexo("Masculino")
                    .profissao("Professor")
                    .build();
            User user1 = modelMapper.map(userPostDto1, User.class);
            UserPostDto userPostDto2 = UserPostDto.builder()
                    .name("maria")
                    .email("@saw")
                    .cpf("3341455453")
                    .sexo("Feminino")
                    .profissao("Professora")
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

    @Nested
    @Transactional
    class TestFiltragem {
        @Autowired
        UserRepository userRepository;
        @Autowired
        CategoriaRepository categoriaRepository;
        @Autowired
        CompanyRepository companyRepository;
        @Autowired
        FabricanteRepository fabricanteRepository;

        @Autowired
        ProdutoRepository produtoRepository;
        Produto produto;
        Produto produto1;
        Company company;
        Company company1;
        Fabricante fabricante;
        Categoria categoria;
        @BeforeEach
        void setup() {
            company = Company.builder()
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

            produto1 =  produtoRepository.save(Produto.builder()
                    .name("Alvejante")
                    .company(company1)
                    .codigoDeBarras("23456789")
                    .dataFabricação(new Date("12/03/2023"))
                    .dataValidade(new Date("12/01/2024"))
                    .fabriante(fabricante)
                    .preco_compra(23.00)
                    .preco_venda(21.00)
                    .descricao("Produto produzido ecologicamente.")
                    .quantidade(123)
                    .categoria(categoria)
                    .avaliacoesProduto(new ArrayList<>())
                    .build());
            company.getCompanyProducts().add(produto);
            company1.getCompanyProducts().add(produto1);
            companyRepository.save(company1);
            companyRepository.save(company);
        }

        @AfterEach
        void tearDown() {
            userRepository.deleteAll();
            companyRepository.deleteAll();
            categoriaRepository.deleteAll();
            fabricanteRepository.deleteAll();
            produtoRepository.deleteAll();
        }

        @Test
        @DisplayName("Buscando o menor preço de um produto de qualquer empresa a partir de uma categoria")
        void testFiltragemMenorPrecoPorCategoria() throws Exception{
            //Arrange
            //Act
            String responseJSONString = driver.perform(get(URI_USUARIO + "/" + categoria.getId() + "/filtragem-preco-menor")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJSONString, Produto.class);
            //Assert
            assertEquals(21.00, resultado.getPreco_venda());
        }

        @Test
        @DisplayName("Buscando o maior preço de um produto de qualquer empresa a partir de uma categoria")
        void testFiltragemMaiorPrecoPorCategoria() throws Exception{
            //Arrange
            //Act
            String responseJSONString = driver.perform(get(URI_USUARIO + "/" + categoria.getId() + "/filtragem-preco-maior")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            Produto resultado = objectMapper.readValue(responseJSONString, Produto.class);
            //Assert
            assertEquals(30.00, resultado.getPreco_venda());
        }
    }

    @Nested
    @Transactional
    class TestUserAvaliacao {
        @Autowired
        UserRepository userRepository;
        @Autowired
        CategoriaRepository categoriaRepository;
        @Autowired
        CompanyRepository companyRepository;
        @Autowired
        FabricanteRepository fabricanteRepository;

        @Autowired
        ProdutoRepository produtoRepository;
        Produto produto;
        Produto produto1;
        Company company;
        Fabricante fabricante;
        Categoria categoria;
        @BeforeEach
        void setup() {
            company = Company.builder()
                    .chaveDeAcesso("12345")
                    .name("Casas Bahia")
                    .cnpj("122133")
                    .email("casas@gmail.com")
                    .descricao("empresa voltada para o ramo de vendas.")
                    .telefones(new HashSet<>())
                    .companyProducts(new ArrayList<>())
                    .avaliacoesCompany(new HashSet<>())
                    .build();

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

            produto1 =  produtoRepository.save(Produto.builder()
                    .name("Alvejante")
                    .company(company)
                    .codigoDeBarras("23456789")
                    .dataFabricação(new Date("12/03/2023"))
                    .dataValidade(new Date("12/01/2024"))
                    .fabriante(fabricante)
                    .preco_compra(23.00)
                    .preco_venda(21.00)
                    .descricao("Produto produzido ecologicamente.")
                    .quantidade(123)
                    .categoria(categoria)
                    .avaliacoesProduto(new ArrayList<>())
                    .build());
            //company.getCompanyProducts().add(produto);
            //company1.getCompanyProducts().add(produto1);
            companyRepository.save(company);
        }

        @AfterEach
        void tearDown() {
            userRepository.deleteAll();
            companyRepository.deleteAll();
            categoriaRepository.deleteAll();
            fabricanteRepository.deleteAll();
            produtoRepository.deleteAll();
        }

        @Test
        @DisplayName("Avaliando produto")
        void testAvaliacaoProduto() throws Exception{
            //Arrange
            AvaliacaoProdutoPostDto avaliacaoProdutoPostDto = AvaliacaoProdutoPostDto.builder()
                    .nota(8.5)
                    .descricaoAvaliador("Produto com bom custo benefício")
                    .qtdEstrelas(5.0)
                    .build();
            //Act
            String responseJSONString = driver.perform(post(URI_USUARIO + "/" + produto.getID() + "/avalia-produto")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(avaliacaoProdutoPostDto)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            //Assert
            assertEquals(1, produto.getAvaliacoesProduto().size());
        }

        @Test
        @DisplayName("Avaliando company")
        void testAvaliacaoCompany() throws Exception{
            //Arrange
            AvaliacaoCompanyPostDto avaliacaoCompanyPostDto = AvaliacaoCompanyPostDto.builder()
                    .nota(8.5)
                    .descricaoAvaliador("Produto com bom custo benefício")
                    .qtdEstrelas(5.0)
                    .build();
            //Act
            String responseJSONString = driver.perform(post(URI_USUARIO + "/" + company.getID() + "/avalia-company")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(avaliacaoCompanyPostDto)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

            //Assert
            assertEquals(1, company.getAvaliacoesCompany().size());
        }

    }
}
