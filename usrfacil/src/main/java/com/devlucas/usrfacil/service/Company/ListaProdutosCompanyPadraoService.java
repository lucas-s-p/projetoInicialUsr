package com.devlucas.usrfacil.service.Company;

import com.devlucas.usrfacil.exception.Company.CompanyNaoExisteException;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaProdutosCompanyPadraoService implements ListaProdutosCompanyService{
    @Autowired
    CompanyRepository companyRepository;
    @Override
    public List<Produto> listaProdutosCompany(Long idCompany) {
        Company company = companyRepository.findById(idCompany).orElseThrow(CompanyNaoExisteException::new);
        return company.getCompanyProducts();
    }
}
