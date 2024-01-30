package com.devlucas.usrfacil.Notificacao;

import com.devlucas.usrfacil.dto.Company.CompanyAtualizaValorProdutoDto;
import com.devlucas.usrfacil.model.*;
import com.devlucas.usrfacil.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@DisplayName("Classe de testes sobre notificação de clientes em relação a promoções")
public class NotificacaoV1PromocaoTests {
    final String URI_USUARIO = "/v1/users";
    final String URI_EMPRESA = "/v1/company";
    @Autowired
    MockMvc driver;

    ObjectMapper objectMapper = new ObjectMapper();
    ModelMapper modelMapper = new ModelMapper();
    @Nested
    @Transactional
    @DisplayName("Classe de testes relacionada a adição de cliente a interesse em promoção.")
    class TestAddCliente {
        @Autowired
        UserRepository userRepository;
        @Autowired
        CompanyRepository companyRepository;
        @Autowired
        ProdutoRepository produtoRepository;
        @Autowired
        FabricanteRepository fabricanteRepository;
        @Autowired
        CategoriaRepository categoriaRepository;
        Company company;
        User user;
        Produto produto;
        Fabricante fabricante;
        Categoria categoria;
        @BeforeEach
        void setup() {
            user = userRepository.save(User.builder()
                    .name("Lucas")
                    .email("lucas.pereira@ccc")
                    .cpf("214335363")
                    .sexo("Masculino")
                    .profissao("Estudante")
                    .chaveDeAcesso("123456")
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
            company= companyRepository.save(Company.builder()
                    .chaveDeAcesso("12345")
                    .name("Casas Bahia")
                    .cnpj("122133")
                    .email("casas@gmail.com")
                    .descricao("empresa voltada para o ramo de vendas.")
                    .telefones(new HashSet<>())
                    .companyProducts(new ArrayList<>())
                    .notificaSourceCliente(new NotificaSourceCliente())
                    .build());
            company.getCompanyProducts().add(produto);
        }
        @AfterEach
        void afterEach() {
            categoriaRepository.deleteAll();
            companyRepository.deleteAll();
            produtoRepository.deleteAll();
            fabricanteRepository.deleteAll();
        }

        @Test
        @DisplayName("Adicionando cliente ao interesse de promoção relacionado a produto")
        void testQuandoAdicionoClienteAoInteresseDePromocao() throws Exception{
            //Arrange
            //Act
            String responseJSONString = driver.perform(patch(URI_USUARIO + "/addCliente" + "/" + user.getID() + "/" + company.getID())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            //Assert
        }

        @Test
        @DisplayName("Alterando o valor de venda de um produto")
        void testalterandoValorDeVendaDoProduto() throws Exception {
            //Arrange
            testQuandoAdicionoClienteAoInteresseDePromocao();
            CompanyAtualizaValorProdutoDto companyAtualizaValorProdutoDto = CompanyAtualizaValorProdutoDto.builder()
                    .valor(20.00)
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
            assertEquals(20.00, resultado.getPreco_venda());
            assertEquals(20.00, company.getCompanyProducts().get(0).getPreco_venda());
        }
    }
}
