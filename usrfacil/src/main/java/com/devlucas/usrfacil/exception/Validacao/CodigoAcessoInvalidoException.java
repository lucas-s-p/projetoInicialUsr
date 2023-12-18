package com.devlucas.usrfacil.exception.Validacao;

import com.devlucas.usrfacil.exception.CommerceException;

public class CodigoAcessoInvalidoException extends CommerceException {
    public CodigoAcessoInvalidoException() {
        super("Código Acesso Inválido");
    }
}
