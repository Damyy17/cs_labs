package com.example.lastlabcs.controller;

import com.example.lastlabcs.entity.User;
import com.example.lastlabcs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forRoleUser"})
    @RolesAllowed("ROLE_USER")
    public String forRoleUser(){
        return "You have access to Caesar and Vigenere Ciphers";
    }

    @GetMapping({"/forAdminUser"})
    @RolesAllowed("ROLE_ADMIN")
    public String forAdminUser(){
        return "You have access to Affine and PlayFair Ciphers";
    }
}
