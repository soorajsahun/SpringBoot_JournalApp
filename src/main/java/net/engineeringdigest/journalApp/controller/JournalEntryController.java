//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//    private Map<Long, JournalEntry> journalEntryMap = new HashMap<>();
//
//    @GetMapping()
//    public ArrayList<JournalEntry> getAll() {// public as to accessible for all
//        return new ArrayList<>(journalEntryMap.values());
//    }
//
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry journalEntry) {
//        journalEntryMap.put(journalEntry.getId(), journalEntry);
//        return true;
//    }
//
//    @GetMapping("/id/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable Long myId) {// public as to accessible for all
//        return journalEntryMap.get(myId);
//    }
//
//    @DeleteMapping("/id/{myId}")
//    public JournalEntry deleteJournalEntryById(@PathVariable Long myId) {// public as to accessible for all
//        return journalEntryMap.remove(myId);
//    }
//
//    @PutMapping("/id/{myId}")
//    public boolean updateEntry(@PathVariable Long myId,@RequestBody JournalEntry journalEntry) {
//        journalEntryMap.put(myId, journalEntry);
//        return true;
//    }
//}