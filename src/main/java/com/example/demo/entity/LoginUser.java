package com.example.demo.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginUser {

    @NonNull
    private String userName;

    @NonNull
    private String password;

}
