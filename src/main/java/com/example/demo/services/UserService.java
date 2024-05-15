package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.error_handler.APIErrorHandler;
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

    public Optional<User> findByID(ObjectId userID){

        return userRepository.findById(userID);
    }

    public void deleteByID(ObjectId userID){

        userRepository.deleteById(userID);
    }

    public Optional<User> findByUserName(String username){
        return userRepository.findByUserName(username);
    }

    public void updatePassword(Map<String, String> body, String userName){
        Optional<User> userInDB = findByUserName(userName);
        if(userInDB.isPresent()) {
            User user = userInDB.get();

            if( !isPasswordMatched(body.get("password"), user.getPassword())) {
            throw new APIErrorHandler("Password Doesn't matched",HttpStatus.UNAUTHORIZED);
            }

            user.setPassword(passwordEncoder.encode(body.get("confirmPassword")));
            saveUser(user);
        }
        throw new APIErrorHandler("User Not Found", HttpStatus.NOT_FOUND);
    }

    public boolean isPasswordMatched(String passwordToMatched, String passwordFromDB){
        return passwordEncoder.matches(passwordToMatched,passwordFromDB);
    }


}
