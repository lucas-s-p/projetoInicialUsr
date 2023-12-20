package com.devlucas.usrfacil.service.validation;

import com.devlucas.usrfacil.exception.Validacao.CodigoDeBarrasInvalidoException;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

@Builder
@Data
@Service
public class ProdutoValidaCodigoDeBarrasService {
    public  boolean validaCodigoDeBarras(String codigoBarras) {
        if(codigoBarras.substring(0,2).equals("789")
        || codigoBarras.substring(8,11).equals("0000")
        || codigoBarras.length() != 13) {
            throw new CodigoDeBarrasInvalidoException();
        }
        String[] listCodBarras = codigoBarras.split("");
        int resul_impar = 0;
        int resul_par = 0;
        for (int i = 0; i < listCodBarras.length - 1; i++) {
            if (i % 2 != 0) {
                resul_impar += Integer.parseInt(listCodBarras[i]) * 3;
            } else {
                resul_par += Integer.parseInt(listCodBarras[i]);
            }
        }
        int soma = resul_impar + resul_par + Integer.parseInt(listCodBarras[listCodBarras.length-1]);
        if (soma % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
