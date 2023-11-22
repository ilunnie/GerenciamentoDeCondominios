package com.gerenciador.projetodevapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("block")
public class BlockModel {
    @Id
    private String id;
    private String name;
    @DBRef
    private List<ApartmentModel> apartments;
}
