package com.devlucas.usrfacil.Notificacao;

import com.devlucas.usrfacil.exception.User.UserNaoExisteException;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_notifica_promocao")
public class NotificaSourceCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("id")
    @Column(name = "id_notifica")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty("clientes")
    private Set<User> clientes = new HashSet<>();

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
