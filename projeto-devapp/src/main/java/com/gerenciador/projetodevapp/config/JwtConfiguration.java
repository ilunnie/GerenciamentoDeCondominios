package com.gerenciador.projetodevapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;
import java.util.HashMap;

import com.gerenciador.projetodevapp.model.UserModel;
import com.gerenciador.projetodevapp.request.JwtBodyRequest;
import com.google.gson.Gson;

public class JwtConfiguration {
    private static final String SECRET_KEY = System.getenv("SECRET_KEY");
    private static final long EXPIRATION_TIME = 86400000 * 7;

    public static String createJwt(String identity, String name, Boolean isAdm)
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

    public static String createJwt(UserModel user)
    {
        return createJwt(user.getIdentity(), user.getName(), user.getIsAdm());
    }

    public static JwtBodyRequest verifyJwt(String jwt)
    {
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