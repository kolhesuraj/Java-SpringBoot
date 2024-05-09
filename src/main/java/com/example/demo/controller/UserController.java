package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
         List<User> all = userService.getAll();
         return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        String userName = (String) request.getAttribute("userName");

        Optional<User> UserInDB = userService.findByUserName(userName);
        if(UserInDB.isPresent()){
            UserInDB.get().setPassword(user.getPassword());
            userService.saveUser(UserInDB.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
