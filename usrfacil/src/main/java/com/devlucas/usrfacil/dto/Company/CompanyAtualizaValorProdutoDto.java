package com.devlucas.usrfacil.dto.Company;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAtualizaValorProdutoDto {
    @JsonProperty("valor")
    @NotBlank(message = "Valor é obrigatório!")
    private Double valor;
}
