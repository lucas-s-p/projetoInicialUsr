package com.devlucas.usrfacil.exception.Categoria;

import com.devlucas.usrfacil.exception.CommerceException;

public class CategoriaNaoExisteException extends CommerceException {
    public CategoriaNaoExisteException() {super("Categoria não existe!");}
}
