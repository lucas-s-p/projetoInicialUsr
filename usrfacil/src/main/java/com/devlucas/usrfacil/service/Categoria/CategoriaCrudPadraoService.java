package com.devlucas.usrfacil.service.Categoria;

import com.devlucas.usrfacil.dto.Categoria.CategoriaPostDto;
import com.devlucas.usrfacil.model.Categoria;
import com.devlucas.usrfacil.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriaCrudPadraoService implements CategoriaCrudService{
    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Categoria categoriaCreate(CategoriaPostDto companyDto) {
        return null;
    }

    @Override
    public void categoriaDelete(Long id) {

    }

    @Override
    public Categoria categoriaFindById(Long id) {
        return null;
    }

    @Override
    public List<Categoria> categoriaFindAll() {
        return null;
    }
}
