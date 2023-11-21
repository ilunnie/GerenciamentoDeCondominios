package com.gerenciador.projetodevapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gerenciador.projetodevapp.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {
}
