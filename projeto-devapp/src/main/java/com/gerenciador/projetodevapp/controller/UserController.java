package com.gerenciador.projetodevapp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerenciador.projetodevapp.config.HashConfiguration;
import com.gerenciador.projetodevapp.model.UserModel;
import com.gerenciador.projetodevapp.request.ImageRequest;
import com.gerenciador.projetodevapp.request.LoginRequest;
import com.gerenciador.projetodevapp.request.UserRequest;
import com.gerenciador.projetodevapp.service.JwtService;
import com.gerenciador.projetodevapp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    private JwtService jwt = new JwtService();

    @GetMapping("")
    public Stream<UserRequest> getAllUser() {
        List<UserModel> listRes = userService.findAll();
        return listRes.stream().map(
                user -> new UserRequest(user.getIdentity(), "*****", user.getName(), user.getImage(), user.getIsAdm()));
    }

    @GetMapping("login")
    public String getUserLogin(@RequestBody LoginRequest request) throws JsonProcessingException {
        Optional<UserModel> user = userService.findById(request.getIdentity());
        if (!user.isPresent()) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", 500);
            error.put("error", "User not founded");
            return new ObjectMapper().writeValueAsString(error);
        }
        if (!HashConfiguration.compareHash(user.get().getPassword(), request.getPassword())) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", 401);
            error.put("error", "Password error");
            return new ObjectMapper().writeValueAsString(error);
        }
        return jwt.createJwt(user.get());
    }

    @PostMapping("")
    public void newUser(@RequestBody UserRequest newUser) {
        if (newUser.getIsAdm() == null)
            newUser.setIsAdm(false);
        var password = HashConfiguration.generateHash(newUser.getPassword());
        userService.save(new UserModel(newUser.getIdentity(), password, newUser.getName(), newUser.getImage(),
                newUser.getIsAdm()));
    }

    @PutMapping("password")
    public void uploadPassword(@RequestBody Map<String, Object> body) {
        String token = (String) body.get("token");
        String id = jwt.verifyJwt(token).getIdentity();

        Optional<UserModel> search = userService.findById(id);
        if (search.isPresent()) {
            UserModel user = search.get();
            var password = HashConfiguration.generateHash((String) body.get("password"));
            user.setPassword(password);
            userService.save(user);
        }
    }

    @PutMapping("image")
    public void uploadImage(@RequestBody ImageRequest request) throws IOException {

        System.out.println("=====================");
        System.out.println(request.getToken());
        System.out.println(request.getImage());
        System.out.println("=====================");
    }
}
