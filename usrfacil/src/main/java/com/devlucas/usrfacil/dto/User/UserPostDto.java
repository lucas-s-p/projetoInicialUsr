package com.devlucas.usrfacil.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDto {
    @JsonProperty("chaveDeAcesso")
    @NotBlank(message = "Chave de acesso deve ser obrigatório!")
    private String chaveDeAcesso;
    @JsonProperty("name")
    @NotBlank(message = "Nome deve ser obrigatório!")
    private String name;
    @JsonProperty("email")
    @NotBlank(message = "Email deve ser obrigatório!")
    private String email;
    @JsonProperty("cpf")
    @NotBlank(message = "Cpf deve ser obrigatório!")
    private String cpf;
    @JsonProperty("profissao")
    @NotBlank(message = "Profisssão deve ser obrigatório!")
    private String profissao;
    @JsonProperty
    @NotBlank(message = "Sexo deve ser obrigatório!")
    private String sexo;
}
