package com.devlucas.usrfacil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_departament")   // Notação do jpa para nomear a tabela do banco de dados.
public class User {
    @Id  // Para dizer que será a chave primária do banco.
    @GeneratedValue(strategy =  GenerationType.IDENTITY)  // com isso o banco de dados autoincrementa o id.
    private Long ID;
    private String name;
    private String email;
    @ManyToOne    // Muitos para um. Cada user com um departamento.
    @JoinColumn(name = "departament_id") // configurando o nome da chave estrangeira do banco;
    private Deparment deparment;

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deparment getDeparment() {
        return deparment;
    }

    public void setDeparment(Deparment deparment) {
        this.deparment = deparment;
    }
}
