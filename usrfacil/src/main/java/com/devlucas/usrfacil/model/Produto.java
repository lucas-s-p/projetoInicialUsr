package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_produto")
public class Produto {
    @ManyToOne
    @JoinColumn(name = "company")
    Company company;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @JsonProperty("name")
    private String name;
    @JsonProperty("preco")
    private Double preco;
    @JsonProperty("fabricante")
    private String fabriante;
    @JsonProperty("codigoDeBarras")
    private String codigoDeBarras;
}
