package com.gerenciador.projetodevapp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private UserModel owner;
    @DBRef
    private List<UserModel> residents;

    public Map<String, Object> ToMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", this.id);
        map.put("number", this.number);
        map.put("owner", this.owner);
        map.put("residents", this.residents);

        return map;
    }
}
