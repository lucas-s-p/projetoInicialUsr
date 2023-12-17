package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.dto.Avaliacao.AvaliacaoCompanyPostDto;
import com.devlucas.usrfacil.dto.Avaliacao.AvaliacaoProdutoPostDto;
import com.devlucas.usrfacil.exception.Produto.ProdutoNaoExisteException;
import com.devlucas.usrfacil.model.Avaliacao;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAvaliaPadraoServicce implements UserAvaliaService{
    @Autowired
    ProdutoRepository produtoRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public void userAvaliaProduto(AvaliacaoProdutoPostDto avaliacaoProdutoPostDto, Long idProduto) {
        Avaliacao avaliacao = modelMapper.map(avaliacaoProdutoPostDto, Avaliacao.class);
        Produto produto = produtoRepository.findById(idProduto).orElseThrow(ProdutoNaoExisteException::new);
        produto.getAvaliacoesProduto().add(avaliacao);
        produtoRepository.save(produto);
    }

    @Override
    public void userAvaliaCompany(AvaliacaoCompanyPostDto avaliacaoCompanyPostDto, Long idCompany) {

    }
}
