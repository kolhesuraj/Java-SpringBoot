package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

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


}
