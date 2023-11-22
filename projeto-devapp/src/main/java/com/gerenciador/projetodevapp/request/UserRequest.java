package com.gerenciador.projetodevapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String identity;
    private String name;
    private Byte[] image;
    private Boolean isAdm;
}