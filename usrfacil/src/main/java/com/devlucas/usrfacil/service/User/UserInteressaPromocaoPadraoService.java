package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.Notificacao.NotificaSourceCliente;
import com.devlucas.usrfacil.exception.Company.CompanyNaoExisteException;
import com.devlucas.usrfacil.exception.User.UserNaoExisteException;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.CompanyRepository;
import com.devlucas.usrfacil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInteressaPromocaoPadraoService implements UserInteressaPromocaoService{
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public void addInteresseCliente(Long idUser, Long idCompany) {
        Company company = companyRepository.findById(idCompany).orElseThrow(CompanyNaoExisteException::new);
        User user = userRepository.findById(idUser).orElseThrow(UserNaoExisteException::new);
        company.getNotificaSourceCliente().addCliente(user);
        companyRepository.save(company);
    }
}
