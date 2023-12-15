package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_avaliacao")
    private Long id;

    @JsonProperty("nota")
    @Column(name = "ds_nota", nullable = false)
    private Double nota;

    @JsonProperty("descricao")
    @Column(name = "ds_descricao", nullable = false)
    private String descricaoAvaliador;
    @JsonProperty("estrelas")
    @Column(name = "estrelas", nullable = false)
    private Double qtdEstrelas;
    @JoinColumn(name = "produto")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Produto produto;
    @JoinColumn(name = "company")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Company company;
}
