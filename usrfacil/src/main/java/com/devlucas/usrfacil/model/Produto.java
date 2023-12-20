package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    @JsonProperty("preco_compra")
    @Column(name="ds_preco_compra", nullable = false)
    private Double preco_compra;
    @JsonProperty("preco_venda")
    @Column(name="ds_preco_venda", nullable = false)
    private Double preco_venda;
    @JsonProperty("quantidade")
    @Column(name="ds_quantidade", nullable = false)
    private Integer quantidade;
    @JsonProperty("descricao")
    @Column(name = "ds_descricao", nullable = false)
    private String descricao;
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
    @JsonProperty("codigoBarras")
    @Column(name = "ds_codigo_barras")
    private String codigoBarras;
    @JsonProperty("dataValidade")
    @Column(name="ds_data_validade", nullable = true)
    private Date dataValidade;
    @JsonProperty("company")
    @JoinColumn(name = "fk_id_company")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Company company;
    @JsonProperty("categoria")
    @JoinColumn(name = "fk_id_categoria")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Categoria categoria;
    @JsonProperty("avaliacaoProduto")
    @Column(name = "avaliacaoProduto")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoesProduto;
}
