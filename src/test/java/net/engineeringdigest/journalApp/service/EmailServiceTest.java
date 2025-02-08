package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Autowired


    @Test
    public void sendeMailTest() {
        emailService.sendeMail("soorajsahun@gmail.com", "Testing Java Mail Sender", "Hi");
    }
}
