package com.gerenciador.projetodevapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document("user")
public class UserModel {
    @Id
    @Indexed(unique = true)
    private String identity;
    private byte[] password;
    private String name;
    private Byte[] image;
    private Boolean isAdm;
}
