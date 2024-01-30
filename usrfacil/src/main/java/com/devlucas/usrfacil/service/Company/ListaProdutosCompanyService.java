package com.devlucas.usrfacil.service.Company;

import com.devlucas.usrfacil.model.Produto;

import java.util.List;

@FunctionalInterface
public interface ListaProdutosCompanyService {
    List<Produto> listaProdutosCompany(Long idCompany);
}
