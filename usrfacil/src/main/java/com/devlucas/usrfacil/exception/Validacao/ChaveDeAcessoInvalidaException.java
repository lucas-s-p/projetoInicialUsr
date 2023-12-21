package com.devlucas.usrfacil.exception.Validacao;

import com.devlucas.usrfacil.exception.CommerceException;

public class ChaveDeAcessoInvalidaException extends CommerceException {
    public ChaveDeAcessoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
