package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")   // Notação do jpa para nomear a tabela do banco de dados.
@Builder   // Se não usar o builder eu não consigo criar um produto nos testes usando, ex: User.builder()
@Data      // Se não usar eu não consigo nos testes acessar a classe, ex: getName(), setName(),....
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id  // Para dizer que será a chave primária do banco.
    @GeneratedValue(strategy =  GenerationType.IDENTITY)  // com isso o banco de dados autoincrementa o id.
    @Column(name="id_user")
    private Long ID;
    @JsonProperty("name")
    @Column(name="ds_name", nullable = false)
    private String name;
    @JsonProperty("email")
    @Column(name="ds_email", nullable = false)
    private String email;
}
