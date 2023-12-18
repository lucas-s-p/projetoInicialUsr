package com.devlucas.usrfacil.service.Company;

import com.devlucas.usrfacil.exception.Company.CompanyNaoExisteException;
import com.devlucas.usrfacil.exception.Produto.ProdutoNaoExisteException;
import com.devlucas.usrfacil.exception.Validacao.CodigoAcessoInvalidoException;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.repository.CompanyRepository;
import com.devlucas.usrfacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyAdicionaObjetosPadraoService implements CompanyAdicionaObjetosService{
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public void adicionaProduto(Long idProduto, String codigoAcesso) {
        Produto produto = produtoRepository.findById(idProduto).orElseThrow(ProdutoNaoExisteException::new);
        Company company = companyRepository.findById(produto.getCompany().getID()).orElseThrow(CompanyNaoExisteException::new);
        if (!company.getChaveDeAcesso().equals(codigoAcesso)) {
           throw new CodigoAcessoInvalidoException();
        }
        company.getCompanyProducts().add(produto);
    }
}
