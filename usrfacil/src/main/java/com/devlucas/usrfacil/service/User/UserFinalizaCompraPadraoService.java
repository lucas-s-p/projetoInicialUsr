package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.exception.Produto.ProdutoNaoExisteException;
import com.devlucas.usrfacil.exception.User.UserNaoExisteException;
import com.devlucas.usrfacil.model.Distribuidora;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.DistribuidoraRepository;
import com.devlucas.usrfacil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFinalizaCompraPadraoService implements UserFinalizaCompraService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    DistribuidoraRepository distribuidoraRepository;
    @Override
    public void finalizaCompra(Long idUser, Long idDistribuidora) {
        User user = userRepository.findById(idUser).orElseThrow(UserNaoExisteException::new);
        Distribuidora distribuidora = distribuidoraRepository.findById(idDistribuidora).orElseThrow(ProdutoNaoExisteException::new);
        distribuidora.getPedidosEmEspera().add(user.getCarrinho());
    }
}
