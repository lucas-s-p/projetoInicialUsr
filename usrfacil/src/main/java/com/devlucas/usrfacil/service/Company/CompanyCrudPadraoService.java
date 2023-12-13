package com.devlucas.usrfacil.service.Company;

import com.devlucas.usrfacil.dto.Company.CompanyPostDto;
import com.devlucas.usrfacil.exception.Company.CompanyNaoExisteException;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.repository.CompanyRepository;
import com.devlucas.usrfacil.repository.UserRepository;
import com.devlucas.usrfacil.service.Company.CompanyCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyCrudPadraoService implements CompanyCrudService {
    @Autowired
    private CompanyRepository companyRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Company companyCreate(CompanyPostDto companyDto) {
        return companyRepository.save(modelMapper.map(companyDto, Company.class));
    }

    @Override
    public void companyDelete(Long id) {
        companyRepository.deleteById(id);
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
