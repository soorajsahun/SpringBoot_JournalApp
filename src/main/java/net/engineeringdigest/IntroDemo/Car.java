package net.engineeringdigest.IntroDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Car {

    @Autowired
    private Dog dog;

    @GetMapping("/abc")
    public String sayHello(){
        return dog.sayHello();
    }
}
