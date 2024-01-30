package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.model.User;

@FunctionalInterface
public interface UserInteressaPromocaoService {
    void addInteresseCliente(User user, Long idCompany);
}
