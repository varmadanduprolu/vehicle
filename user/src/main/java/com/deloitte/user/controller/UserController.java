package com.deloitte.user.controller;

import com.deloitte.user.entity.Users;
import com.deloitte.user.model.UserLogin;
import com.deloitte.user.model.UserRegistry;
import com.deloitte.user.model.UserResponse;
import com.deloitte.user.model.UserUpdate;
import com.deloitte.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public Users getUserById(@PathVariable("id") Integer user_id){
        return userService.getUserById(user_id);
    }
    @PostMapping()
    public String userRegistration(@RequestBody UserRegistry userRegistry){
        return userService.userRegistration(userRegistry);
    }

    public String userLogin(UserLogin userLogin){
        return null;
    }

    @PutMapping("/{id}")
    public String updateUserDetails(@PathVariable("id") Integer id, @RequestBody UserUpdate userUpdate){
        return userService.updateUserDetails(id,userUpdate);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        return userService.deleteUser(id);
    }
}
