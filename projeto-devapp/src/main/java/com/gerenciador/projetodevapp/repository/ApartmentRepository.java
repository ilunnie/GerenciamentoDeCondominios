package com.gerenciador.projetodevapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gerenciador.projetodevapp.model.ApartmentModel;

public interface ApartmentRepository extends MongoRepository<ApartmentModel, String> {
    List<ApartmentModel> findAllBy(int offset, int limit);
}
