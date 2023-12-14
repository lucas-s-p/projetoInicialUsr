package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "fabricante")
public class Fabricante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id_fabricante")
    private Long id;
    @JsonProperty("nome")
    @Column(name = "ds_nome", nullable = false)
    private String nome;
    @JsonProperty("email")
    @Column(name = "ds_email", nullable = false)
    private String email;
    @JsonProperty("cnpj")
    @Column(name = "ds_cnpj", nullable = false)
    private String cnpj;
    @JsonProperty("descricao")
    @Column(name = "ds_descricao", nullable = false)
    private String descricao;
    @JsonProperty("telefones")
    @Column(name = "ds_telefones", nullable = false)
    @OneToMany(mappedBy = "fabricante", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Telefone> telefones;
}
