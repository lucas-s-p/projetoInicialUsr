package com.devlucas.usrfacil.dto.Categoria;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaPostDto {
    @JsonProperty("name")
    @NotBlank(message = "Nome deve ser obrigatório")
    private String name;
}
