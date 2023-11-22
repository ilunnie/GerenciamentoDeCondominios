package com.gerenciador.projetodevapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.projetodevapp.service.CondominiumService;

@RestController
@RequestMapping("/")
public class CondominiumController {
    @Autowired
    private CondominiumService condominiumService;
}
