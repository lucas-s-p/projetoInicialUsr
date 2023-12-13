package com.devlucas.usrfacil.service.Fabricante;

import com.devlucas.usrfacil.dto.Fabricante.FabricantePostDto;
import com.devlucas.usrfacil.exception.Fabricante.FabricanteNaoExisteException;
import com.devlucas.usrfacil.model.Fabricante;
import com.devlucas.usrfacil.repository.FabricanteRepository;
import com.devlucas.usrfacil.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabricanteCrudPadraoService implements FabricanteCrudService{
    @Autowired
    private FabricanteRepository fabricanteRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Fabricante fabricanteCreate(FabricantePostDto fabricantePostDto) {
        return fabricanteRepository.save(modelMapper.map(fabricantePostDto, Fabricante.class));
    }

    @Override
    public void fabricanteDelete(Long id) {
        fabricanteRepository.deleteById(id);
    }

    @Override
    public Fabricante fabricanteFindById(Long id) {
        return fabricanteRepository.findById(id).orElseThrow(FabricanteNaoExisteException::new);
    }

    @Override
    public List<Fabricante> fabricanteFindAll() {
        return fabricanteRepository.findAll();
    }
}
