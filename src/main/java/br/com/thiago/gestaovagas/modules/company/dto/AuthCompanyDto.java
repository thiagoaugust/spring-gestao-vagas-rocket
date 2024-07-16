package br.com.thiago.gestaovagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDto {

    private String password;
    private String username;
}
