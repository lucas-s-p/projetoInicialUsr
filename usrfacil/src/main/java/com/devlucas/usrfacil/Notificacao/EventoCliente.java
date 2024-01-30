package com.devlucas.usrfacil.Notificacao;

import com.devlucas.usrfacil.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventoCliente {
    private NotificaSourceCliente notificaSourceCliente;
    private Produto produto;
}
