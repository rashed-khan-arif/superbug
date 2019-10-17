package com.startup.superbug.controller;

import com.startup.superbug.core.SBException;
import com.startup.superbug.data_access.service.UserService;
import com.startup.superbug.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping(path = "users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        try {
            Optional<User> user = userService.findById(id).get();
            if (user.isPresent()) {
                User data = user.get();
                data.setPassword(null);
                return ResponseEntity.ok(data);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("users")
    public ResponseEntity saveUser(@RequestBody User user) {
        try {
            if (user != null) {
                if (userService.isExits(user.getEmail()).get()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SBException("Email Address Already Exits"));
                }
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                User u = userService.save(user).get();
                if (u != null) {
                    User newUser = userService.findById(u.getUserId()).get().orElse(u);
                    newUser.setPassword(null);
                    return ResponseEntity.ok(newUser);
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
