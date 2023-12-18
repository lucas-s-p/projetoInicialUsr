package com.devlucas.usrfacil.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
    @JsonProperty("email")
    @Column(name = "ds_email", nullable = false)
    private String email;
    @JsonProperty("cnpj")
    @Column(name = "ds_cnpj", nullable = false)
    private String cnpj;
    @JsonProperty("descricao")
    @Column(name = "ds_descricao", nullable = false)
    private String descricao;
    @JsonProperty("telefones")
    @Column(name = "ds_telefones", nullable = false)
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Telefone> telefones;
    @Column(name="ds_company")
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)   //EAGER permite que a colecao seja carregada imediatamente.
    private List<Produto> companyProducts;
    @JsonProperty("avaliacaoCompany")
    @Column(name = "ds_avaliacaoCompany")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Avaliacao> avaliacoesCompany;
}