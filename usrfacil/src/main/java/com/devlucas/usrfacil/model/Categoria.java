package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_categoria")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "id_categoria")
    private Long id;
    @Column(name= "ds_nome",  nullable = false)
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("produto")
    @OneToMany(mappedBy = "categoria", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Produto> produto;
}
