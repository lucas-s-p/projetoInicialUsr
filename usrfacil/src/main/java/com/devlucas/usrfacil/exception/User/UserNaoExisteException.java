package com.devlucas.usrfacil.exception.User;

import com.devlucas.usrfacil.exception.CommerceException;

public class UserNaoExisteException extends CommerceException {
    public UserNaoExisteException() { super("Usuario nao existe"); }
}
