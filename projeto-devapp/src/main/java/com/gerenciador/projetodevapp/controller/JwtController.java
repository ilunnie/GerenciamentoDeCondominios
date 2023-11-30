package com.gerenciador.projetodevapp.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.projetodevapp.request.JwtBodyRequest;
import com.gerenciador.projetodevapp.service.JwtService;


@RestController
@RequestMapping("/jwt")
public class JwtController {
    private JwtService jwt = new JwtService();

    @PostMapping("validate")
    public boolean validate(@RequestBody Map<String, Object> body) {
        String token = (String) body.get("token");
        return jwt.verifyJwt(token) != null;
    }

    @PostMapping("")
    public JwtBodyRequest getPayload(@RequestBody Map<String, Object> body) {

        String token = (String) body.get("token");
        var payload = jwt.verifyJwt(token);

        return payload;
    }
}
