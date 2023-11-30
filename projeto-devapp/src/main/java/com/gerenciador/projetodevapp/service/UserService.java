package com.gerenciador.projetodevapp.service;

import java.util.List;
import java.util.Optional;

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

    public void save(String identity, byte[] password, String name, Byte[] image) {
        this.userRepository.save(new UserModel(
            identity, password, name, image, false
        ));
    }

    public List<UserModel> findAll() {
        return (List<UserModel>) this.userRepository.findAll();
    }

    public Optional<UserModel> findById(String identity) {
        return this.userRepository.findById(identity);
    }

    public void deleteById(String identity) {
        this.userRepository.deleteById(identity);
    }
}
