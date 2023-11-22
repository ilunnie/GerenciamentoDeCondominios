package com.gerenciador.projetodevapp.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String identity;
    private String password;
}
