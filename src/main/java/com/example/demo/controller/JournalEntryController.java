package com.example.demo.controller;

import com.example.demo.dto.JournalEntryDTO;
import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import com.example.demo.services.JournalEntryService;
import com.example.demo.services.UserService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAll(@PathVariable String userName){
        Optional<User> user = userService.findByUserName(userName);
        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         List<JournalEntry> all = user.get().getJournalEntries();
         if(all != null && !all.isEmpty()){
             return new ResponseEntity<>(all, HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntryDTO> createEntry(@RequestBody JournalEntryDTO myEntry, @PathVariable String userName){
        try{
            ModelMapper modelMapper = new ModelMapper();
            JournalEntry newEntry = modelMapper.map(myEntry, JournalEntry.class);

            journalEntryService.saveEntry(newEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myID}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myID){

        Optional<JournalEntry> entryData = journalEntryService.findByID(myID);
        return entryData.map(journalEntry -> new ResponseEntity<>(journalEntry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("id/{userName}/{myID}")
    public  ResponseEntity<String> deleteJournalEntryById(@PathVariable ObjectId myID, @PathVariable String userName){
        journalEntryService.deleteByID(myID, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{myID}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId myID,@RequestBody JournalEntryDTO newEntry){
        try {
            Optional<JournalEntry> optionalEntry = journalEntryService.findByID(myID);

            if (optionalEntry.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            JournalEntry oldEntry = optionalEntry.get();

            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            oldEntry.setTitle(!newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());

            journalEntryService.saveEntry(oldEntry);

            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
