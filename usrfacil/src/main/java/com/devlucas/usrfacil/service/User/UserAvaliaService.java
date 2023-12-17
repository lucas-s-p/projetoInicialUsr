package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.dto.Avaliacao.AvaliacaoCompanyPostDto;
import com.devlucas.usrfacil.dto.Avaliacao.AvaliacaoProdutoPostDto;

public interface UserAvaliaService {
    void userAvaliaProduto(AvaliacaoProdutoPostDto avaliacaoProdutoPostDto, Long idProduto);
    void userAvaliaCompany(AvaliacaoCompanyPostDto avaliacaoCompanyPostDto, Long idCompany);
}
