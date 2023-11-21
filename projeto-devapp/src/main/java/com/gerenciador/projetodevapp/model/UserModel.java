package com.gerenciador.projetodevapp.model;

import java.nio.Buffer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("user")
public class UserModel {
    @Id
    @Indexed(unique = true)
    private String identity;
    private String password;
    private String name;
    private Buffer image;
    private Boolean isAdm;
}
