package com.devlucas.usrfacil.service.Company;

import com.devlucas.usrfacil.dto.Company.CompanyAtualizaValorProdutoDto;
import com.devlucas.usrfacil.model.Produto;

@FunctionalInterface
public interface ModificaValorProdutoService {
    Produto modificaValorProduto(CompanyAtualizaValorProdutoDto companyAtualizaValorProdutoDto, Long idCompany, Long idproduto, String codigoAcesso);
}
