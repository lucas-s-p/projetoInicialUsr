package com.devlucas.usrfacil.service.validation;

import com.devlucas.usrfacil.exception.Validacao.CodigoAcessoInvalidoException;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Builder
@Data
public class ValidaChaveAcessoService {
    public boolean validaChave(String chaveAcesso) {
        if (chaveAcesso.length() > 12 || chaveAcesso.length() < 6) {
            return false;
        }
        String[] list = chaveAcesso.split("");
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i].equals(list[i+1])) {
                return false;
            }
        }
        return true;
    }
}
