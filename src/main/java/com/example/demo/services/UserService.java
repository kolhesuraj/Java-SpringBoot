package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.errorHandler.APIErrorHandler;
import com.example.demo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    public List<User> getAll(){

        return userRepository.findAll();
    }

    public Optional<User> findByID(ObjectId ID){

        return userRepository.findById(ID);
    }

    public void deleteByID(ObjectId ID){

        userRepository.deleteById(ID);
    }

    public Optional<User> findByUserName(String username){
        return userRepository.findByUserName(username);
    }

    public void updatePassword(Map<String, String> Body, String userName){
        Optional<User> UserInDB = findByUserName(userName);
        if(UserInDB.isPresent()) {
            User user = UserInDB.get();
            System.out.println(isPasswordMatched(Body.get("password"), user.getPassword()));
            if( !isPasswordMatched(Body.get("password"), user.getPassword())) {
            throw new APIErrorHandler("Password Doesn't matched",HttpStatus.UNAUTHORIZED);
            }
            user.setPassword(passwordEncoder.encode(Body.get("confirmPassword")));
            saveUser(user);
        }
        throw new APIErrorHandler("User Not Found", HttpStatus.NOT_FOUND);
    }

    public boolean isPasswordMatched(String passwordToMatched, String passwordFromDB){
        System.out.println(passwordToMatched+":"+passwordFromDB);
        return passwordEncoder.matches(passwordToMatched,passwordFromDB);
    }


}
