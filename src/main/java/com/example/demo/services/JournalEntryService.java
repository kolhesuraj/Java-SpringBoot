package com.example.demo.services;

import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import com.example.demo.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId ID){
        return journalEntryRepository.findById(ID);
    }

    public void deleteByID(ObjectId ID, String userName){
        Optional<User> user = userService.findByUserName(userName);
        if(user.isPresent()) {
            user.get().getJournalEntries().removeIf(x -> x.getId().equals(ID));
            userService.saveUser(user.get());
            journalEntryRepository.deleteById(ID);
        }
    }


}
