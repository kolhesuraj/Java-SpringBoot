package com.example.demo.controller;


import com.example.demo.entity.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.errorHandler.APIErrorHandler;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.JWTTokenService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JWTTokenService jwtTokenService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try {
            Optional<User> isUser = userService.findByUserName(user.getUserName());
            if (isUser.isPresent()) {
                return new ResponseEntity<>("User Already Exists!", HttpStatus.BAD_REQUEST);
            }
            authenticationService.addUser(user);
            return new ResponseEntity<>("User Registered Successfully!", HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Internal Server Error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginUser user){
        try {
           boolean isValid = authenticationService.ValidateUser(user);
           if(isValid) {
               final String Token = jwtTokenService.generateToken(user);
               final String message= "User Registered Successfully!";

               Map<String, Object> responseBody = new HashMap<>();
               responseBody.put("message", message);
               responseBody.put("token", Token);

               return new ResponseEntity<>(responseBody, HttpStatus.OK);
           }
           return new ResponseEntity<>("User Not Found!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            throw new APIErrorHandler("Internal Server Error: "+e.getMessage());
        }
    }
}
