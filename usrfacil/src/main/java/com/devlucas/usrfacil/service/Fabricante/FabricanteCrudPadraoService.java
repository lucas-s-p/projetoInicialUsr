package com.devlucas.usrfacil.service.Fabricante;

import com.devlucas.usrfacil.dto.Fabricante.FabricantePostDto;
import com.devlucas.usrfacil.model.Fabricante;
import com.devlucas.usrfacil.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabricanteCrudPadraoService implements FabricanteCrudService{
    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Fabricante fabricanteCreate(FabricantePostDto companyDto) {
        return null;
    }

    @Override
    public void fabricanteDelete(Long id) {

    }

    @Override
    public Fabricante fabricanteFindById(Long id) {
        return null;
    }

    @Override
    public List<Fabricante> fabricanteFindAll() {
        return null;
    }
}
