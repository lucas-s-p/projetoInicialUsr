package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.Notificacao.NotificaSourceCliente;
import com.devlucas.usrfacil.exception.Company.CompanyNaoExisteException;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInteressaPromocaoPadraoService implements UserInteressaPromocaoService{
    @Autowired
    CompanyRepository companyRepository;
    NotificaSourceCliente notificaSourceCliente = new NotificaSourceCliente();
    @Override
    public void addInteresseCliente(User user, Long idCompany) {
        Company company = companyRepository.findById(idCompany).orElseThrow(CompanyNaoExisteException::new);
        company.getNotificaSourceCliente().addCliente(user);
    }
}
