package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tb_funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;
    @JsonProperty("nome")
    @Column(name = "ds_nome", nullable = false)
    private String nome;
    @JsonProperty("idade")
    @Column(name = "ds_idade", nullable = false)
    private Integer idade;
    @JsonProperty("ocupacao")
    @Column(name = "ds_ocupacao", nullable = false)
    private String ocupacao;
    @JsonProperty("telefones")
    @JoinColumn(name = "ds_telefones")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Telefone telefone;
}
