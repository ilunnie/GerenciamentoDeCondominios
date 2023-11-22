package com.gerenciador.projetodevapp.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashConfiguration {
    public static byte[] generateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static boolean compareHash(byte[] hash, String input) {
        byte[] secondHash = generateHash(input);
        return Arrays.equals(hash, secondHash);
    }
}
