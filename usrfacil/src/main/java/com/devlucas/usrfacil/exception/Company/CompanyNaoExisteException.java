package com.devlucas.usrfacil.exception.Company;

import com.devlucas.usrfacil.exception.CommerceException;

public class CompanyNaoExisteException extends CommerceException {
    public CompanyNaoExisteException() { super("Empresa nao existe"); }
}
