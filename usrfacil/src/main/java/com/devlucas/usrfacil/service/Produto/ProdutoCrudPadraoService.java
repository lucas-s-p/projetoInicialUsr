package com.devlucas.usrfacil.service.Produto;

import com.devlucas.usrfacil.dto.Produto.ProdutoPostDto;
import com.devlucas.usrfacil.exception.Produto.ProdutoNaoExisteException;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoCrudPadraoService implements ProdutoCrudService{
    @Autowired
    private ProdutoRepository produtoRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Produto produtoCreate(ProdutoPostDto produtoDto) {
        return produtoRepository.save(modelMapper.map(produtoDto, Produto.class));
    }

    @Override
    public void produtoDelete(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto produtoFindById(Long id) {
        return produtoRepository.findById(id).orElseThrow(ProdutoNaoExisteException::new);
    }

    @Override
    public List<Produto> produtoFindAll() {
        return produtoRepository.findAll();
    }
}
