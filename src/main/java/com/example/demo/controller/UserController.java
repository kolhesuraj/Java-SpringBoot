package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDTO;
import com.example.demo.errorHandler.APIErrorHandler;
import com.example.demo.errorHandler.GlobalErrorHandler;
import com.example.demo.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;


    @GetMapping
    public ResponseEntity<UserDTO> getLoggedInUser(){
        String userName = (String) request.getAttribute("userName");

        Optional<User> UserInDB = userService.findByUserName(userName);

        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDto = modelMapper.map(UserInDB, UserDTO.class);

         return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, String> user){
        try {
            String userName = (String) request.getAttribute("userName");
            if (!userName.isEmpty()) {
                userService.updatePassword(user, userName);
                return new ResponseEntity<>("Password Updated successfully!", HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
           throw new APIErrorHandler(e.getMessage());
        }
    }


}
