package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_company")
public class Company {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name= "id_company")
    private Long ID;
    @JsonProperty("name")
    @Column(name="ds_nome", nullable = false)
    private String name;
    //@JoinColumn(name="fk_id_company")
    @Column(name="ds_company")
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)   //EAGER permite que a colecao seja carregada imediatamente.
    private List<Produto> companyProducts;
}