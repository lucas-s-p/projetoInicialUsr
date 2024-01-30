package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.exception.User.UserNaoExisteException;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdicionaProdCarrinhoPadraoService  implements UserAdicionaProdCarrinhoService{
    @Autowired
    UserRepository userRepository;
    @Override
    public void addProdutoAoCarrinho(Long idUser, Long idProduto) {
        User user = userRepository.findById(idUser).orElseThrow(UserNaoExisteException::new);
        user.getCarrinho().getObjetosCarrinho().add(idProduto);
    }
}
