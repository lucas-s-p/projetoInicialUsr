package com.devlucas.usrfacil.dto.Categoria;

import com.devlucas.usrfacil.model.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaPostDto {
    @JsonProperty("name")
    @NotBlank(message = "Nome deve ser obrigatório")
    private String nome;

    @JsonProperty("produto")
    @NotBlank(message = "Produto deve ser obrigatório")
    private List<Produto> produto;
}
