package com.devlucas.usrfacil.dto.Produto;

import com.devlucas.usrfacil.model.Avaliacao;
import com.devlucas.usrfacil.model.Categoria;
import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Fabricante;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPostDto {
    @JsonProperty("nome")
    @NotBlank(message = "Nome deve ser obrigatório!")
    private String name;
    @JsonProperty("preco_compra")
    @NotBlank(message = "Preço de compra deve ser obrigatório!")
    private Double preco_compra;
    @JsonProperty("preco_venda")
    @NotBlank(message = "Preço de venda deve ser obrigatório!")
    private Double preco_venda;
    @JsonProperty("quantidade")
    @NotBlank(message = "Quantidade deve ser obrigatório!")
    private Integer quantidade;
    @JsonProperty("descricao")
    @NotBlank(message = "Descrição deve ser obrigatório!")
    private String descricao;
    @JsonProperty("fabricante")
    @NotBlank(message = "Fabricante deve ser obrigatório!")
    private Fabricante fabriante;
    @JsonProperty("codigoDeBarras")
    @NotBlank(message = "Código de Barras deve ser obrigatório!")
    private String codigoDeBarras;
    @JsonProperty("dataFabricacao")
    @NotBlank(message = "Data de fabricação deve ser obrigatório!")
    private Date dataFabricação;
    @JsonProperty("dataValidade")
    @NotBlank(message = "Data de validade deve ser obrigatório!")
    private Date dataValidade;
    @JsonProperty("company")
    @NotBlank(message = "Empresa deve ser obrigatório!")
    Company company;
    @JsonProperty("categoria")
    @NotBlank(message = "Categoria deve ser obrigatório!")
    private Categoria categoria;
    @JsonProperty("telefones")
    @NotBlank(message = "Avaliação deve ser obrigatório!")
    private List<Avaliacao> avaliacoesProduto;
}
