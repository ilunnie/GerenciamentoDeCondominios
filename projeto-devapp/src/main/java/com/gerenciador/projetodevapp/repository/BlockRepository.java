package com.gerenciador.projetodevapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gerenciador.projetodevapp.model.BlockModel;

public interface BlockRepository extends MongoRepository<BlockModel, String> {
}
