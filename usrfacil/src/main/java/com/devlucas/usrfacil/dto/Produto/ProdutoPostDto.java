package com.devlucas.usrfacil.dto.Produto;

import com.devlucas.usrfacil.model.Company;
import com.devlucas.usrfacil.model.Fabricante;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPostDto {
    @JsonProperty("nome")
    @NotBlank(message = "Nome deve ser obrigatório")
    private String name;
    @JsonProperty("preco")
    @NotBlank(message = "Preço deve ser obrigatório")
    private Double preco;
    @JsonProperty("fabricante")
    @NotBlank(message = "Fabricante deve ser obrigatório")
    private Fabricante fabriante;
    @JsonProperty("codigoDeBarras")
    @NotBlank(message = "Código de Barras deve ser obrigatório")
    private String codigoDeBarras;
    @JsonProperty("dataFabricacao")
    @NotBlank(message = "Data de fabricação deve ser obrigatório")
    private Date dataFabricação;
    @JsonProperty("dataValidade")
    @NotBlank(message = "Data de validade deve ser obrigatório")
    private Date dataValidade;
    @JsonProperty("company")
    @NotBlank(message = "Empresa deve ser obrigatório")
    Company company;
}
