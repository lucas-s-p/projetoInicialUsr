package com.devlucas.usrfacil.Notificacao;

import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "tb_notifica_promocao")
public class NotificaSourceCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> clientes = new ArrayList<>();

    public void notificaClientePromocao(Produto produto) {
        EventoCliente eventoCliente = new EventoCliente(this, produto);
        this.fireNotificacao(eventoCliente);
    }

    public void addCliente(User user) {
        clientes.add(user);
    }
    public void removeCliente(User user) {
        clientes.remove(user);
    }

    private void fireNotificacao(EventoCliente eventoCliente) {
        for (User user: clientes) {
            user.notifica(eventoCliente);
        }
    }

}
