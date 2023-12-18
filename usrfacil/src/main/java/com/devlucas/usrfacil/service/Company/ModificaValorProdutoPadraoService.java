package com.devlucas.usrfacil.service.Company;

import com.devlucas.usrfacil.dto.Company.CompanyAtualizaValorProdutoDto;
import com.devlucas.usrfacil.exception.Company.CompanyNaoExisteException;
import com.devlucas.usrfacil.exception.Validacao.CodigoAcessoInvalidoException;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.repository.CompanyRepository;
import com.devlucas.usrfacil.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModificaValorProdutoPadraoService implements ModificaValorProdutoService{
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Produto modificaValorProduto(CompanyAtualizaValorProdutoDto companyAtualizaValorProdutoDto, Long idCompany, Long idproduto, String codigoAcesso) {
        Company company = companyRepository.findById(idCompany).orElseThrow(CompanyNaoExisteException::new);
        if(!company.getChaveDeAcesso().equals(codigoAcesso)) {
            throw new CodigoAcessoInvalidoException();
        }
        Produto produtoX = new Produto();
        for (Produto produto : company.getCompanyProducts()) {
            if (produto.getID().equals(idproduto)) {
                modelMapper.map(produto, produtoX);
            }
        }
        produtoX.setPreco_venda(companyAtualizaValorProdutoDto.getValor());
        companyRepository.save(company);
        return produtoRepository.save(produtoX);
    }
}
