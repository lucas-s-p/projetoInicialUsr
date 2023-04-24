package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_company")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long ID;
    @JsonProperty("name")
    private String name;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)   //EAGER permite que a colecao seja carregada imediatamente.
    private List<Produto> companyProducts;
}