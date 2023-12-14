package com.devlucas.usrfacil.dto.Fabricante;

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
public class FabricantePostDto {
    @JsonProperty("name")
    @NotBlank(message = "Nome deve ser obrigatório")
    private String nome;
}
