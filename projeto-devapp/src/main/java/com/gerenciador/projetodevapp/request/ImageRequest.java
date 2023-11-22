package com.gerenciador.projetodevapp.request;

import lombok.Data;

@Data
public class ImageRequest {
    private String token;
    private byte[] image;
}
