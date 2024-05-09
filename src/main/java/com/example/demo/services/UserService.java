package com.example.demo.services;

import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import com.example.demo.repository.JournalEntryRepository;
import com.example.demo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
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

    public void saveNewUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("User"));
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


}
