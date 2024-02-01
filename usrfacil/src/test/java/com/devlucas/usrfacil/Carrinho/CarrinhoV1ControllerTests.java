package com.devlucas.usrfacil.Carrinho;

import com.devlucas.usrfacil.model.*;
import com.devlucas.usrfacil.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("Testes em relação a efetuação da compra de produtos")
public class CarrinhoV1ControllerTests {
    final String URI_USUARIO = "/v1/users";
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
    @Autowired
    DistribuidoraRepository distribuidoraRepository;
    @Autowired
    MockMvc driver;
    ObjectMapper objectMapper = new ObjectMapper();
    ModelMapper modelMapper = new ModelMapper();
    Produto produto;
    Produto produto1;
    Company company;
    Fabricante fabricante;
    Categoria categoria;
    User user;
    Distribuidora distribuidora;
    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        company = Company.builder()
                .chaveDeAcesso("1@2345")
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
        company.getCompanyProducts().add(produto);
        companyRepository.save(company);

        user = userRepository.save(User.builder()
                        .cpf("942050929542")
                        .name("Lucas")
                        .email("lucas@ccc")
                        .sexo("Masculino")
                        .profissao("Estudante")
                        .carrinho(new Carrinho())
                        .chaveDeAcesso("123456")
                .build());
        distribuidora = distribuidoraRepository.save(Distribuidora.builder()
                        .cnpj("12345678")
                        .nomeDistribuidora("Ltda Entregas")
                        .razaoSocial("Entregas")
                        .funcionarios(new HashSet<>())
                        .pedidosEmEspera(new HashSet<>())
                        .telefones(new HashSet<>())
                        .veiculos(new HashSet<>())
                .build());
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
    @DisplayName("Testando a inserção de um produto ao carrinho")
    void testQuandoCompraUmProduto() throws Exception {
        //Arrange
        //Act
        String responseJSONString = driver.perform(patch(URI_USUARIO + "/adiciona-produto/" + produto.getID() + "/" + user.getID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        //Assert
        assertEquals(1, user.getCarrinho().getObjetosCarrinho().size());
        assertEquals(produto.getID(), user.getCarrinho().getObjetosCarrinho().get(0));
    }

    @Test
    @DisplayName("Finalizando a compra no carrinho do cliente")
    void testQuandoFinalizoACompraDosProdutosDoCarrinho() throws Exception {
        //Arrange
        //Act
        String responseJSONString = driver.perform(patch(URI_USUARIO + "/finaliza-compra/" + user.getID() +"/"+ distribuidora.getIdDistribuidora())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        //Assert
    }
}
