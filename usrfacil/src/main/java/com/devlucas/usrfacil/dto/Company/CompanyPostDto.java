package com.devlucas.usrfacil.dto.Company;

import com.devlucas.usrfacil.model.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPostDto {
    @JsonProperty("name")
    @NotBlank(message = "Nome deve ser obrigatório")
    private String name;
    @JsonProperty("produto")
    @NotBlank(message = "Produto deve ser obrigatório")
    private List<Produto> companyProducts;
}
