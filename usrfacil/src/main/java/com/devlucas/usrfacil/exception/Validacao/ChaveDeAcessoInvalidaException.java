package com.devlucas.usrfacil.exception.Validacao;

import com.devlucas.usrfacil.exception.CommerceException;

public class ChaveDeAcessoInvalidaException extends CommerceException {
    public ChaveDeAcessoInvalidaException() {
        super("Chave de acesso inválida!");
    }
}
