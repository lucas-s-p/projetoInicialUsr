package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.dto.User.UserPostDto;
import com.devlucas.usrfacil.exception.User.UserNaoExisteException;
import com.devlucas.usrfacil.exception.Validacao.ChaveDeAcessoInvalidaException;
import com.devlucas.usrfacil.exception.Validacao.CodigoAcessoInvalidoException;
import com.devlucas.usrfacil.model.User;
import com.devlucas.usrfacil.repository.UserRepository;
import com.devlucas.usrfacil.service.validation.ValidaChaveAcessoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCrudPadraoService implements UserCrudService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidaChaveAcessoService validaChaveAcessoService;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public User userCreateService(UserPostDto userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        String respostaValida = validaChaveAcessoService.validaChave(user.getChaveDeAcesso());
        if (!respostaValida.equals("Válida")) {
            throw new ChaveDeAcessoInvalidaException(respostaValida);
        }
        return userRepository.save(user);
    }

    @Override
    public void userDeleteById(Long id, String chaveAcesso) {
        User user = userRepository.findById(id).orElseThrow(UserNaoExisteException::new);
        if (!user.getChaveDeAcesso().equals(chaveAcesso)) {
            throw new CodigoAcessoInvalidoException();
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<User> userFindAllService() {
        return userRepository.findAll();
    }

    @Override
    public User userFindyByIdService(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNaoExisteException::new);
        return user;
    }
}
