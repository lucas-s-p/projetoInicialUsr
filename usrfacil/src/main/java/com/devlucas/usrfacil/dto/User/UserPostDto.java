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

    @JsonProperty("name")
    @NotBlank(message = "Nome deve ser obrigatório")
    private String name;
    @JsonProperty("email")
    @NotBlank(message = "Email deve ser obrigatório")
    private String email;
}
