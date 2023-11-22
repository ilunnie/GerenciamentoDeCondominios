package com.gerenciador.projetodevapp.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import com.gerenciador.projetodevapp.model.UserModel;

public class JwtConfiguration {
    private static final String SECRET_KEY = "$2zOj6C[!}3E";
    private static final long EXPIRATION_TIME = 86400000 * 7;

    public static String createJwt(String identity, String name, Boolean isAdm)
    {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        return jwt;
    }

    public static String createJwt(UserModel user)
    {
        return null;
    }
}
