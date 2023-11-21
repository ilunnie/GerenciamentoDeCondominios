package com.gerenciador.projetodevapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciador.projetodevapp.model.UserModel;
import com.gerenciador.projetodevapp.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel save(UserModel userModel) {
        return this.userRepository.save(userModel);
    }

    public List<UserModel> findAll() {
        return (List<UserModel>) this.userRepository.findAll();
    }
}
