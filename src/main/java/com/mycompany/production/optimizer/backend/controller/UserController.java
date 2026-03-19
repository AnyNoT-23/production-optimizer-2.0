package com.mycompany.production.optimizer.backend.controller;

import com.mycompany.production.optimizer.backend.model.User;
import com.mycompany.production.optimizer.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return service.getUserById(id).orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return service.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        user.setId(id);
        return service.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
    
}
