package com.gerenciador.projetodevapp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.projetodevapp.config.HashConfiguration;
import com.gerenciador.projetodevapp.model.UserModel;
import com.gerenciador.projetodevapp.request.ImageRequest;
import com.gerenciador.projetodevapp.request.LoginRequest;
import com.gerenciador.projetodevapp.request.UserRequest;
import com.gerenciador.projetodevapp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public Stream<UserRequest> getAllUser() {
        List<UserModel> listRes = userService.findAll();
        return listRes.stream().map(user -> new UserRequest(user.getIdentity(), user.getName(), user.getImage(), user.getIsAdm()));
    }

    @GetMapping("login")
    public String getUserLogin(@RequestBody LoginRequest request) {
        Optional<UserModel> user = userService.findById(request.getIdentity());
        if (user == null) {
            return "User not founded";
        }
        if (HashConfiguration.compareHash(user.get().getPassword(), request.getPassword())) {
            
        }
        return "Password error";
    }

    @PostMapping("")
    public void newUser(@RequestBody UserModel newUser) {
        if(newUser.getIsAdm() == null)
            newUser.setIsAdm(false);
        userService.save(newUser);
    }

    @PutMapping("/image")
    public void uploadImage(@RequestBody ImageRequest request) throws IOException {

        System.out.println("=====================");
        System.out.println(request.getToken());
        System.out.println(request.getImage());
        System.out.println("=====================");
    }
}
