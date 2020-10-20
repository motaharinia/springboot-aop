package com.motaharinia.aop.presentation.controller;


import com.motaharinia.aop.business.UserService;
import com.motaharinia.aop.presentation.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserModel create(@RequestBody @Validated  UserModel userModel) {
        return userService.create(userModel);
    }

    @GetMapping("/user/{id}")
    public UserModel findOne(@PathVariable Integer id) {
        return userService.findOne(id);
    }

    @GetMapping("/user")
    public List<UserModel> findAll() {
        return userService.findAll();
    }

    @PutMapping("/user")
    public UserModel update(@RequestBody @Validated  UserModel userModel) {
        return userService.update(userModel);
    }

    @DeleteMapping("/user/{id}")
    public UserModel delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
