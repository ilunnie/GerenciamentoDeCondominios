package com.gerenciador.projetodevapp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gerenciador.projetodevapp.config.HashConfiguration;
import com.gerenciador.projetodevapp.model.UserModel;
import com.gerenciador.projetodevapp.request.ImageRequest;
import com.gerenciador.projetodevapp.request.JwtBodyRequest;
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

    @PostMapping("login")
    public ResponseEntity<?> getUserLogin(@RequestBody LoginRequest request) throws JsonProcessingException {
        Optional<UserModel> user = userService.findById(request.getIdentity());
        if (!user.isPresent()) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", 500);
            error.put("error", "User not found");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
        if (!HashConfiguration.compareHash(user.get().getPassword(), request.getPassword())) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", 401);
            error.put("error", "Password error");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        return ResponseEntity.ok(jwt.createJwt(user.get()));
    }

    @PostMapping("")
    public void newUser(@RequestBody UserRequest newUser) {
        if (newUser.getIsAdm() == null)
            newUser.setIsAdm(false);
        var password = HashConfiguration.generateHash(newUser.getPassword());
        userService.save(new UserModel(newUser.getIdentity(), password, newUser.getName(), newUser.getImage(),
                newUser.getIsAdm()));
    }

    @PutMapping("adm/{id}")
    public ResponseEntity<?> defineAdm(@RequestBody Map<String, Object> body, @PathVariable String id) {
        String token = (String) body.get("token");
        JwtBodyRequest sender = jwt.verifyJwt(token);
        if (!sender.getIsAdm()) {
            Map<String, Object> error = new HashMap<>();
            error.put("status", 401);
            error.put("error", "Operation denied");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        Optional<UserModel> search = userService.findById(id);
        if (search.isPresent()) {
            UserModel user = search.get();
            user.setIsAdm(!user.getIsAdm());
            userService.save(user);
            return ResponseEntity.ok("Operation performed");
        }
        Map<String, Object> error = new HashMap<>();
        error.put("status", 500);
        error.put("error", "User not found");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
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

        // ToDo não finalizado
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@RequestBody Map<String, Object> body, @PathVariable String id) {
        String token = (String) body.get("token");
        JwtBodyRequest payload = jwt.verifyJwt(token);

        if (payload != null && payload.getIsAdm() == true) {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("status", HttpStatus.UNAUTHORIZED.value());
            error.put("error", "Access denied");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}
