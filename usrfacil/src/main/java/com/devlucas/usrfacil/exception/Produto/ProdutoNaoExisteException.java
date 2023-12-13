package com.devlucas.usrfacil.exception.Produto;

import com.devlucas.usrfacil.exception.CommerceException;

public class ProdutoNaoExisteException extends CommerceException {
    public ProdutoNaoExisteException() {super("Produto não existe");}

}
