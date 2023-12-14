package com.devlucas.usrfacil.service.Produto;

import com.devlucas.usrfacil.dto.Produto.ProdutoPostDto;
import com.devlucas.usrfacil.exception.Categoria.CategoriaNaoExisteException;
import com.devlucas.usrfacil.exception.Company.CompanyNaoExisteException;
import com.devlucas.usrfacil.exception.Fabricante.FabricanteNaoExisteException;
import com.devlucas.usrfacil.exception.Produto.ProdutoNaoExisteException;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.repository.CategoriaRepository;
import com.devlucas.usrfacil.repository.CompanyRepository;
import com.devlucas.usrfacil.repository.FabricanteRepository;
import com.devlucas.usrfacil.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoCrudPadraoService implements ProdutoCrudService{
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    FabricanteRepository fabricanteRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Produto produtoCreate(ProdutoPostDto produtoDto, Long companyId, Long fabricanteId, Long categoriaId) {
        var company = companyRepository.findById(companyId).orElseThrow(CompanyNaoExisteException::new);
        var fabricante = fabricanteRepository.findById(fabricanteId).orElseThrow(FabricanteNaoExisteException::new);
        var categoria = categoriaRepository.findById(categoriaId).orElseThrow(CategoriaNaoExisteException::new);
        Produto produto = modelMapper.map(produtoDto, Produto.class);
        produto.setCompany(company);
        produto.setFabriante(fabricante);
        produto.setCategoria(categoria);
        return produtoRepository.save(produto);
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
