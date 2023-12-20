package com.devlucas.usrfacil.dto.Company;

import com.devlucas.usrfacil.model.Avaliacao;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.model.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPostDto {
    @JsonProperty("name")
    @NotBlank(message = "Nome deve ser obrigatório!")
    private String name;
    @JsonProperty("chaveDeAcesso")
    @NotBlank(message = "Chave de acesso deve ser obrigatório!")

    private String chaveDeAcesso;
    @JsonProperty("produto")
    @NotBlank(message = "Produto deve ser obrigatório!")
    private List<Produto> companyProducts;
    @JsonProperty("email")
    @NotBlank(message = "Email deve ser obrigatório!")
    private String email;
    @JsonProperty("cnpj")
    @NotBlank(message = "Cnpj deve ser obrigatório!")
    private String cnpj;
    @JsonProperty("descricao")
    @NotBlank(message = "Descrição deve ser obrigatório!")
    private String descricao;
    @JsonProperty("telefones")
    @NotBlank(message = "Telefone deve ser obrigatório!")
    private Set<Telefone> telefones;
    @JsonProperty("avaliações")
    @NotBlank(message = "Avaliação deve ser obrigatório!")
    private List<Avaliacao> avCompany;

}
