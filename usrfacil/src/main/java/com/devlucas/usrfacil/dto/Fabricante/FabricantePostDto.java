package com.devlucas.usrfacil.dto.Fabricante;

import com.devlucas.usrfacil.model.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FabricantePostDto {
    @JsonProperty("name")
    @NotBlank(message = "Nome deve ser obrigatório!")
    private String nome;
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
    private List<Telefone> telefones;
}
