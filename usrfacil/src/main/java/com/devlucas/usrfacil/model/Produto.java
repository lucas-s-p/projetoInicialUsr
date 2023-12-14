package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @JoinColumn(name="ds_fabricante")
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Fabricante fabriante;
    @JsonProperty("codigoDeBarras")
    @Column(name="ds_cod_barras", nullable = false)
    private String codigoDeBarras;
    @JsonProperty("dataFabricacao")
    @Column(name="ds_data_fabricacao", nullable = false)
    private Date dataFabricação;
    @JsonProperty("dataValidade")
    @Column(name="ds_data_validade", nullable = true)
    private Date dataValidade;
    @JsonProperty("company")
    @JoinColumn(name = "fk_id_company")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Company company;
}
