package com.gerenciador.projetodevapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gerenciador.projetodevapp.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {
    @Query("{'identity': ?0}")
    Optional<UserModel> findById(String identity);
}
