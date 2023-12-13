package com.devlucas.usrfacil.Company;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Classe de Testes sobre Empresa")
public class CompanyV1ControllerTests {
    final String URI_EMPRESA = "/v1/company";
}
