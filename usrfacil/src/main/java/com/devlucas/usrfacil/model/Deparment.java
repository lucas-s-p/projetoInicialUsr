package com.devlucas.usrfacil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user")
public class Deparment {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long ID;

    private String name;
    public Deparment() {

    }
    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
