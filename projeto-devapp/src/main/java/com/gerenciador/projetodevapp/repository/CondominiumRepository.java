package com.gerenciador.projetodevapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gerenciador.projetodevapp.model.CondominiumModel;

public interface CondominiumRepository extends MongoRepository<CondominiumModel, String> {
}
