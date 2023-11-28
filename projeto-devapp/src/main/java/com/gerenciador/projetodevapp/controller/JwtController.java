package com.gerenciador.projetodevapp.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.projetodevapp.service.JwtService;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    private JwtService jwt = new JwtService();

    @GetMapping("validate")
    public boolean validate(@RequestBody Map<String, Object> body) {
        String token = (String) body.get("token");
        return jwt.verifyJwt(token) != null;
    }
}
