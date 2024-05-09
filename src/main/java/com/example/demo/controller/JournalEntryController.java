//package com.example.demo.controller;
//
//import com.example.demo.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
public class JournalEntryController {
//
//    private Map<String, JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry){
//        journalEntries.put(myEntry.getId(),myEntry);
//        return true;
//    }
//
//    @GetMapping("id/{myID}")
//    public JournalEntry getJournalEntryById(@PathVariable String myID){
//        return journalEntries.get(myID);
//    }
//
//    @DeleteMapping("id/{myID}")
//    public  JournalEntry deleteJournalEntryById(@PathVariable String myID){
//        return journalEntries.remove(myID);
//    }
//
//    @PutMapping("id/{myID}")
//    public JournalEntry updateJournalEntry(@PathVariable String myID,@RequestBody JournalEntry myEntry){
//        return journalEntries.put(myID,myEntry);
//    }
}
