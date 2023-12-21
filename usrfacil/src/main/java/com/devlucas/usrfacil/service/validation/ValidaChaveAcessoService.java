package com.devlucas.usrfacil.service.validation;

import com.devlucas.usrfacil.exception.Validacao.ChaveDeAcessoInvalidaException;
import com.devlucas.usrfacil.exception.Validacao.CodigoAcessoInvalidoException;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Builder
@Data
public class ValidaChaveAcessoService {
    public String validaChave(String chaveAcesso) {
        if (chaveAcesso.length() > 12 || chaveAcesso.length() < 6) {
           return "A chave de acesso deve ter entre 6 e 12 caracteres.";
        } else if (!contemCaractereEspecial(chaveAcesso)) {
            return "A chave de acesso deve conter um caractere especial.";
        }
        String[] list = chaveAcesso.split("");
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i].equals(list[i+1])) {
                return "A chave de acesso não pode ter dois caracteres consecutivos iguais";
            }
        }
        return "Válida";
    }

    public boolean contemCaractereEspecial(String chaveAcesso) {
        return chaveAcesso.matches(".*[@!#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }

}
