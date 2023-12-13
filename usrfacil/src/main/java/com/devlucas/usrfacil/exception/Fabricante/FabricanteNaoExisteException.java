package com.devlucas.usrfacil.exception.Fabricante;

import com.devlucas.usrfacil.exception.CommerceException;

public class FabricanteNaoExisteException extends CommerceException {
    public FabricanteNaoExisteException(){super("Fabricante não existe!");}
}
