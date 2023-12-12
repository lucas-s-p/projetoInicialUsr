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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produto")
    private Long ID;
    @JsonProperty("nome")
    @Column(name="ds_nome", nullable = false)
    private String name;
    @JsonProperty("preco")
    @Column(name="ds_preco", nullable = false)
    private Double preco;
    @JsonProperty("fabricante")
    @Column(name="ds_fabricante", nullable = false)
    private String fabriante;
    @JsonProperty("codigoDeBarras")
    @Column(name="ds_cod_barras", nullable = false)
    private String codigoDeBarras;
    @JoinColumn(name = "fk_id_company")
    @ManyToOne
    Company company;
}
