package com.devlucas.usrfacil.controller.User;

import com.devlucas.usrfacil.dto.Avaliacao.AvaliacaoCompanyPostDto;
import com.devlucas.usrfacil.dto.Avaliacao.AvaliacaoProdutoPostDto;
import com.devlucas.usrfacil.dto.User.UserPostDto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.service.User.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // configurando a classe para ser um controller e ela vai responder por requesições.
@RequestMapping(value = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserFiltragemService userFiltragemService;

    @Autowired
    private UserAvaliaService userAvaliaService;

    @Autowired
    private UserInteressaPromocaoService userInteressaPromocaoService;

    @Autowired
    private UserAdicionaProdCarrinhoPadraoService userAdicionaProdCarrinhoPadraoService;

    @Autowired
    private UserFinalizaCompraService userFinalizaCompraService;
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userCrudService.userFindAllService());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userCrudService.userFindyByIdService(id));
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserPostDto userPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userCrudService.userCreateService(userPostDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id,
            @RequestParam String codigoAcesso
    ) {
        this.userCrudService.userDeleteById(id, codigoAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("");
    }

    @GetMapping("/{idCategoria}/filtragem-preco-menor")
    public ResponseEntity<?> filtragemMenorPrecoPorCategoria(@PathVariable Long idCategoria) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userFiltragemService.filtragemMenorPreco(idCategoria));
    }

    @GetMapping("/{idCategoria}/filtragem-preco-maior")
    public ResponseEntity<?> filtragemMaiorPrecoPorCategoria(@PathVariable Long idCategoria) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userFiltragemService.filtragemMaiorPreco(idCategoria));
    }

    @PostMapping("/{idProduto}/avalia-produto")
    public ResponseEntity<?> userAvaliaProduto(
            @RequestBody @Valid AvaliacaoProdutoPostDto avaliacaoProdutoPostDto,
            @PathVariable Long idProduto
    ) {
        this.userAvaliaService.userAvaliaProduto(avaliacaoProdutoPostDto, idProduto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("");
    }

    @PostMapping("/{idCompany}/avalia-company")
    public ResponseEntity<?> userAvaliaCompany(
            @RequestBody @Valid AvaliacaoCompanyPostDto avaliacaoCompanyPostDto,
            @PathVariable Long idCompany
    ) {
        this.userAvaliaService.userAvaliaCompany(avaliacaoCompanyPostDto, idCompany);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("");
    }

    @PatchMapping("/addCliente/{idUser}/{idCompany}")
    public ResponseEntity<?> clienteInteressaPromocao(
            @PathVariable Long idUser,
            @PathVariable Long idCompany
    ) {
        this.userInteressaPromocaoService.addInteresseCliente(idUser, idCompany);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }

    @PatchMapping("/adiciona-produto/{idProduto}/{idUser}")
    public ResponseEntity<?> adicionaProdCarrinho(@PathVariable Long idUser, @PathVariable Long idProduto) {
        this.userAdicionaProdCarrinhoPadraoService.addProdutoAoCarrinho(idUser, idProduto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }

    @PatchMapping("/finaliza-compra/{idUser}")
    public ResponseEntity<?> finalizaCompraCarrinho(@PathVariable Long idUser) {
        this.userFinalizaCompraService.finalizaCompra(idUser);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("");
    }
}
