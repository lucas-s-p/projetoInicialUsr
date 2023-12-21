package com.devlucas.usrfacil.service.Company;

import com.devlucas.usrfacil.dto.Company.CompanyPostDto;
import com.devlucas.usrfacil.exception.Company.CompanyNaoExisteException;
import com.devlucas.usrfacil.exception.Validacao.CodigoAcessoInvalidoException;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.repository.CompanyRepository;
import com.devlucas.usrfacil.repository.UserRepository;
import com.devlucas.usrfacil.service.Company.CompanyCrudService;
import com.devlucas.usrfacil.service.validation.ValidaChaveAcessoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyCrudPadraoService implements CompanyCrudService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ValidaChaveAcessoService validaChaveAcessoService;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Company companyCreate(CompanyPostDto companyDto) {
        Company company = modelMapper.map(companyDto, Company.class);
        if (validaChaveAcessoService.validaChave(company.getChaveDeAcesso()) == false) {
            throw new CodigoAcessoInvalidoException();
        }
        return companyRepository.save(company);
    }

    @Override
    public void companyDelete(Long id, String codigoAcesso) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNaoExisteException::new);
        if (company.getChaveDeAcesso().equals(codigoAcesso)) {
            companyRepository.deleteById(id);
        } else {
            throw  new CodigoAcessoInvalidoException();
        }
    }

    @Override
    public Company companyFindById(Long id) {
        return companyRepository.findById(id).orElseThrow(CompanyNaoExisteException::new);
    }

    @Override
    public List<Company> companyFindAll() {
        return companyRepository.findAll();
    }
}
