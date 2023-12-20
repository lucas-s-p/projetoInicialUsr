package com.devlucas.usrfacil.exception.Validacao;

import com.devlucas.usrfacil.exception.CommerceException;

public class CodigoDeBarrasInvalidoException extends CommerceException {
    public CodigoDeBarrasInvalidoException() {
        super("Código de Barras Inválido!");
    }
}
