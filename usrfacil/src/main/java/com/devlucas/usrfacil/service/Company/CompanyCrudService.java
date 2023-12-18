package com.devlucas.usrfacil.service.Company;

import com.devlucas.usrfacil.dto.Company.CompanyPostDto;
import com.devlucas.usrfacil.model.Company;

import java.util.List;

public interface CompanyCrudService {
    Company companyCreate(CompanyPostDto companyDto);
    void companyDelete(Long id, String codigoAcesso);
    Company companyFindById(Long id);
    List<Company> companyFindAll();
}
