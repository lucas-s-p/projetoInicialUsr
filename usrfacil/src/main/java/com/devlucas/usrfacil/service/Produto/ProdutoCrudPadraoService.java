package com.devlucas.usrfacil.service.Produto;

import com.devlucas.usrfacil.dto.Produto.ProdutoPostDto;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoCrudPadraoService implements ProdutoCrudService{
    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Produto produtoCreate(ProdutoPostDto produtoDto) {
        return null;
    }

    @Override
    public void produtoDelete(Long id) {

    }

    @Override
    public Produto produtoFindById(Long id) {
        return null;
    }

    @Override
    public List<Produto> produtoFindAll() {
        return null;
    }
}
