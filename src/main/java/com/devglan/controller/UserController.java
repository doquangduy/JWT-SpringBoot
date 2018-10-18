package com.devglan.controller;

import com.devglan.DTO.UserDTO;
import com.devglan.model.User;
import com.devglan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User Registor(Model model, @RequestBody UserDTO user){
        User u = new User();
        u.setPassword(bCryptPasswordEncoder.encode(user.getPassWord()));
        u.setUsername(user.getUserName());
        return userService.save(u);
    }


}
