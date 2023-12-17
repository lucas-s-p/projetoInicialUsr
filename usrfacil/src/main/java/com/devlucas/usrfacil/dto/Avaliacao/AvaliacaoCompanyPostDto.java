package com.devlucas.usrfacil.dto.Avaliacao;

import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoCompanyPostDto {

    @JsonProperty("nota")
    @NotBlank(message = "Nota deve ser obrigatório")
    private Double nota;

    @JsonProperty("descricao")
    @NotBlank(message = "Descrição deve ser obrigatório")
    private String descricaoAvaliador;
    @JsonProperty("estrelas")
    @NotBlank(message = "Estrelas deve ser obrigatório")
    private Double qtdEstrelas;
    @JoinColumn(name = "company")
    @NotBlank(message = "company deve ser obrigatório")
    private Company company;
}
