package com.gerenciador.projetodevapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("apartment")
public class ApartmentModel {
    @Id
    private String id;
    private int number;
    @DBRef
    private BlockModel block;
    @DBRef
    private UserModel owner;
    @DBRef
    private List<UserModel> residents;
}
