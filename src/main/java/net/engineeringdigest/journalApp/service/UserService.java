package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

//    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    public void createEntry(User user) {
        userRepository.save(user);
    }

    public void createNewEntry(User user) {
        try {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        } catch (Exception e) {
            log.error("Error occurred for {}",user.getUserName(),e);
//            logger.error("Error occurred for {}",user.getUserName(),e);
//            logger.info("");
//            logger.warn("");
            log.debug("Error occurred for {}",user.getUserName(),e);
            throw new RuntimeException(e);
        }
    }

    public void createAdminUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public List<User> getAll() {// public as to accessible for all
        return userRepository.findAll();
    }

    public Optional<User> findByObjectId(ObjectId id) {// public as to accessible for all
        return userRepository.findById(id);
    }

    public void deleteByObjectId(ObjectId id) {// public as to accessible for all
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
