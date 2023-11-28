package com.gerenciador.projetodevapp.request;

import lombok.Data;

@Data
public class JwtBodyRequest {
    private String identity;
    private String name;
    private Boolean isAdm;
}
