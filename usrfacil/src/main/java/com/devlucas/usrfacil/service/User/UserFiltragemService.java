package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.model.Produto;

public interface UserFiltragemService {
    Produto filtragemMenorPreco(Long idCategoria);
    Produto filtragemMaiorPreco(Long idCategoria);
}
