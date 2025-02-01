package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping
//    public List<User> getAllUsers(){
//        return userService.getAll();
//    }

//    @PostMapping
//    public void createUser(@RequestBody User user){
//        userService.createNewEntry(user);
//    }

//    @PutMapping("/{userName}")
//    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
//        User userInDb = userService.findByUserName(userName);
//        if(userInDb!=null){
//            userInDb.setUserName(user.getUserName());
//            userInDb.setPassword(user.getPassword());
//            userService.createEntry(userInDb);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
//        User authenticate hone k baad hi yaha tak aayga
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.createNewEntry(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        userRepository.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}