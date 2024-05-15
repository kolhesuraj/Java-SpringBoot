package com.example.demo.dto;

import com.example.demo.entity.JournalEntry;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {

    private ObjectId id;


    private String userName;


    private String name;


    private List<JournalEntry> journalEntries = new ArrayList<>();

    private List<String> roles;

}
