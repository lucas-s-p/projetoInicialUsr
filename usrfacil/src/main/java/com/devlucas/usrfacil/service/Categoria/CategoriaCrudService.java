package com.devlucas.usrfacil.service.Categoria;

import com.devlucas.usrfacil.dto.Categoria.CategoriaPostDto;
import com.devlucas.usrfacil.model.Categoria;

import java.util.List;

public interface CategoriaCrudService {
    Categoria categoriaCreate(CategoriaPostDto companyDto);
    void categoriaDelete(Long id);
    Categoria categoriaFindById(Long id);
    List<Categoria> categoriaFindAll();
}
