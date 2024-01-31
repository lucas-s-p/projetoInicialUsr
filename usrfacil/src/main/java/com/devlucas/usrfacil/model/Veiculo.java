package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tb_veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;
    @JsonProperty("cor")
    @Column(name = "ds_cor", nullable = false)
    private String cor;
    @JsonProperty("placa")
    @Column(name = "ds_placa", nullable = false)
    private String placa;
    @JsonProperty("modelo")
    @Column(name = "ds_modelo", nullable = false)
    private String modelo;
    @JsonProperty("ano_compra")
    @Column(name = "ds_ano_compra", nullable = false)
    private String anoCompra;
    @JsonProperty("tipo_veiculo")
    @Column(name = "ds_tipo_veiculo", nullable = false)
    private String tipoVeiculo;
}
