package com.devlucas.usrfacil.service.Fabricante;

import com.devlucas.usrfacil.dto.Company.CompanyPostDto;
import com.devlucas.usrfacil.dto.Fabricante.FabricantePostDto;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Fabricante;

import java.util.List;

public interface FabricanteCrudService {
    Fabricante fabricanteCreate(FabricantePostDto companyDto);
    void fabricanteDelete(Long id);
    Fabricante fabricanteFindById(Long id);
    List<Fabricante> fabricanteFindAll();
}
