package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.exception.Categoria.CategoriaNaoExisteException;
import com.devlucas.usrfacil.model.Categoria;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.repository.CategoriaRepository;
import com.devlucas.usrfacil.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFiltragemPadraoService implements UserFiltragemService{
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Override
    public Produto filtragemMenorPreco(Long idCategoria) {
        return realizaFiltragem(true, idCategoria);
    }

    @Override
    public Produto filtragemMaiorPreco(Long idCategoria) {
        return realizaFiltragem(false, idCategoria);
    }


    private Produto realizaFiltragem(Boolean verifica, Long idCategoria) {
        List<Company> companyList = companyRepository.findAll();
        Categoria categoria = categoriaRepository.findById(idCategoria).get();
        if (categoria != null) {
            Produto produtoX = null;
            for (Company company : companyList) {
                List<Produto> produtos = company.getCompanyProducts();
                for (Produto produto : produtos) {
                    if (categoria.getNome().equals(produto.getCategoria().getNome())) {
                        if (produtoX == null) {
                            produtoX = criarCopiaProduto(produto);
                        }
                        if (verifica == true && produtoX.getPreco_venda() > produto.getPreco_venda()) {
                            produtoX = criarCopiaProduto(produto);
                        } else if (verifica == false && produtoX.getPreco_venda() < produto.getPreco_venda()){
                            produtoX = criarCopiaProduto(produto);
                        }
                    }
                }
            }
            return produtoX;
        }  else {
            throw new CategoriaNaoExisteException();
        }
    }

    private Produto criarCopiaProduto(Produto produto) {
        return Produto.builder()
                .name(produto.getName())
                .descricao(produto.getDescricao())
                .dataFabricação(produto.getDataFabricação())
                .dataValidade(produto.getDataValidade())
                .preco_venda(produto.getPreco_venda())
                .build();
    }
}
