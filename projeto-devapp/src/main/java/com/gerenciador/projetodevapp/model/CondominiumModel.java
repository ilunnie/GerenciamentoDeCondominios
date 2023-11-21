package com.gerenciador.projetodevapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("condominium")
public class CondominiumModel {
    @Id
    private String id;
    private String name;
    private String cep;
}
