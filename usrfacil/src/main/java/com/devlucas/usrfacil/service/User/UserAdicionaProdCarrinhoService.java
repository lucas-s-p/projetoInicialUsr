package com.devlucas.usrfacil.service.User;
@FunctionalInterface
public interface UserAdicionaProdCarrinhoService {
    void addProdutoAoCarrinho(Long idUser, Long idProduto);
}
