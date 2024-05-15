package com.example.demo.controller;


import com.example.demo.dto.LoginUser;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.error_handler.APIErrorHandler;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.JWTTokenService;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
@Slf4j
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JWTTokenService jwtTokenService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO user){
        try {
            Optional<User> isUser = userService.findByUserName(user.getUserName());
            if (isUser.isPresent()) {
                return new ResponseEntity<>("User Already Exists!", HttpStatus.BAD_REQUEST);
            }

            ModelMapper modelMapper = new ModelMapper();
            User newUser = modelMapper.map(user, User.class);

            authenticationService.addUser(newUser);

            return new ResponseEntity<>("User Registered Successfully!", HttpStatus.OK);
        }catch (Exception e){
            log.error("Error Occurred",e);
            throw new APIErrorHandler("Internal Server Error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUser user){
        try {
            Map<String, Object> responseBody = new HashMap<>();
           boolean isValid = authenticationService.validateUser(user);
           if(isValid) {
               final String Token = jwtTokenService.generateToken(user);
               final String message= "User Registered Successfully!";


               responseBody.put("message", message);
               responseBody.put("token", Token);

               return new ResponseEntity<>(responseBody, HttpStatus.OK);
           }
            responseBody.put("message", "User Not Found!");
           return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error("Error Occurred",e);
            throw new APIErrorHandler("Internal Server Error: "+e.getMessage());
        }
    }
}
