package com.devlucas.usrfacil.service.Produto;

import com.devlucas.usrfacil.dto.Produto.ProdutoPostDto;
import com.devlucas.usrfacil.model.Produto;

import java.util.List;

public interface ProdutoCrudService {
    Produto produtoCreate(ProdutoPostDto produtoDto, Long companyId, Long fabricanteId);
    void produtoDelete(Long id);
    Produto produtoFindById(Long id);
    List<Produto> produtoFindAll();
}
