package com.gerenciador.projetodevapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.projetodevapp.model.UserModel;
import com.gerenciador.projetodevapp.service.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserModel> getAllUser() {
        List<UserModel> listRes = userService.findAll();
        for (UserModel userModel : listRes) {
            userModel.setPassword("******");
        }
        return listRes;
    }

    @PostMapping("")
    public void newUser(@RequestBody UserModel newUser) {
        if(newUser.getIsAdm() == null)
            newUser.setIsAdm(false);
        userService.save(newUser);
    }
}
