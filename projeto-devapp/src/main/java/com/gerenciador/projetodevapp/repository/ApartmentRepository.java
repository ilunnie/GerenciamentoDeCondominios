package com.gerenciador.projetodevapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gerenciador.projetodevapp.model.ApartmentModel;

public interface ApartmentRepository extends MongoRepository<ApartmentModel, String> {
}
