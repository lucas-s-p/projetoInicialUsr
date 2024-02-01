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
@Table(name = "tb_distribuidora")
public class Distribuidora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_distribuidora", nullable = false)
    private Long idDistribuidora;
    @JsonProperty("nome_distribuidora")
    @Column(name = "ds_nome_distribuidora", nullable = false)
    private String nomeDistribuidora;
    @JsonProperty("cnpj")
    @Column(name = "ds_cnpj", nullable = false)
    private String cnpj;
    @JsonProperty("razaoSocial")
    @Column(name = "ds_razao_social", nullable = false)
    private String razaoSocial;
    @JsonProperty("veiculos")
    @Column(name = "ds_veiculos")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Veiculo> veiculos;
    @JsonProperty("funcionarios")
    @Column(name = "ds_funcionarios")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Funcionario> funcionarios;
    @JsonProperty("telefones")
    @Column(name = "ds_telefones")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Telefone> telefones;

    @JsonProperty("pedidos_em_espera")
    @Column(name = "ds_pedidos_em_espera")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Carrinho> pedidosEmEspera;
}
