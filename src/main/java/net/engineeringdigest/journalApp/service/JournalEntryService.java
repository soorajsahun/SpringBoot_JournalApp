package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
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
    JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void createEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
//      if exception comes then journalEntry is saved but user not saved, so to avoid that we use @Transactional
//      user.setUserName(null);-> deliberately creating exception

        userService.createEntry(user);
    }

    public void createEntry(JournalEntry journalEntry) {
        JournalEntry saved = journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {// public as to accessible for all
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findByObjectId(ObjectId id) {// public as to accessible for all
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteByObjectId(ObjectId id, String userName) {
        boolean removed=false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.createEntry(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return removed;
    }
}
