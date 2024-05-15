package com.example.demo.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginUser {

    @NonNull
    private String userName;

    @NonNull
    private String password;

}
