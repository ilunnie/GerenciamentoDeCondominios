package com.gerenciador.projetodevapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.gerenciador.projetodevapp.model.UserModel;
import com.gerenciador.projetodevapp.request.JwtBodyRequest;
import com.google.gson.Gson;

@Service
public class JwtService {
    private String SECRET_KEY = "senha_de_teste";

    private final static long EXPIRATION_TIME = 86400000 * 7;

    public String createJwt(String identity, String name, Boolean isAdm)
    {
        var user = new HashMap<String, Object>();
        user.put("identity", identity);
        user.put("name", name);
        user.put("isAdm", isAdm);
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        String jwt = Jwts.builder()
                .setSubject(user.toString())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        return jwt;
    }

    public String createJwt(UserModel user)
    {
        return createJwt(user.getIdentity(), user.getName(), user.getIsAdm());
    }

    public JwtBodyRequest verifyJwt(String jwt)
    {
        if (jwt == null)
            return null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();

            Gson gson = new Gson();
            return gson.fromJson(claims.getSubject(), JwtBodyRequest.class);
        } catch (SignatureException e) {
            return null;
        }
    }
}