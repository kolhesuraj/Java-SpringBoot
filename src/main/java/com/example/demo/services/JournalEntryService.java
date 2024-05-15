package com.example.demo.services;

import com.example.demo.config.JWTFilter;
import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import com.example.demo.error_handler.APIErrorHandler;
import com.example.demo.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            Optional<User> user = userService.findByUserName(userName);
            if(user.isPresent()) {
                journalEntry.setDate(LocalDateTime.now());
                JournalEntry saved = journalEntryRepository.save(journalEntry);
                user.get().getJournalEntries().add(saved);
                userService.saveUser(user.get());
            }
        } catch (Exception e) {
            throw new APIErrorHandler("An error occurred while saving the entry."+ e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId journalEntryID){
        return journalEntryRepository.findById(journalEntryID);
    }

    public void deleteByID(ObjectId journalEntryID, String userName){
        Optional<User> user = userService.findByUserName(userName);
        if(user.isPresent()) {
            user.get().getJournalEntries().removeIf(x -> x.getId().equals(journalEntryID));
            userService.saveUser(user.get());
            journalEntryRepository.deleteById(journalEntryID);
        }
    }


}
