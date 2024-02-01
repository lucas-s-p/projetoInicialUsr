package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.exception.Produto.ProdutoNaoExisteException;
import com.devlucas.usrfacil.exception.User.UserNaoExisteException;
import com.devlucas.usrfacil.model.Distribuidora;
import com.devlucas.usrfacil.model.Produto;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.DistribuidoraRepository;
import com.devlucas.usrfacil.repository.ProdutoRepository;
import com.devlucas.usrfacil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFinalizaCompraPadraoService implements UserFinalizaCompraService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    DistribuidoraRepository distribuidoraRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Override
    public void finalizaCompra(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(UserNaoExisteException::new);
        //Distribuidora que tiver menor quantidade de pedidos receberá o pedido do cliente.
        if (distribuidoraRepository.existsById(0L)) {
            Distribuidora distribuidoraP = distribuidoraRepository.findById(0L).get();
            for (Distribuidora distribuidora : distribuidoraRepository.findAll()) {
                if (distribuidoraP.getPedidosEmEspera().size() > distribuidora.getPedidosEmEspera().size()) {
                    distribuidoraP = distribuidora;
                }
            }
        }
        //Produto tem a quantidade modificada quando o pedido é finalizado.
        for(Long idProd: user.getCarrinho().getObjetosCarrinho()) {
            Produto produto = produtoRepository.findById(idProd).orElseThrow(ProdutoNaoExisteException::new);
            produto.setQuantidade(produto.getQuantidade() - 1);
            produtoRepository.save(produto);
        }
    }
}
