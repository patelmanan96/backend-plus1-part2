package com.example.backendplus1part2.services;

import com.example.backendplus1part2.models.User;
import com.example.backendplus1part2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(allowCredentials = "true",origins = "*")
public class UserService {

    @Autowired
    UserRepository repository;

    @PostMapping("/api/register")
    public User register(@RequestBody User user) {
        User check = repository.findByUsername(user.getUsername());
        if(check != null && check.getUsername() != null){
            return new User();
        }
        repository.save(user);
        return user;
    }


    @PostMapping("/api/login")
    public User login(@RequestBody User user) {
        User toLogin = repository.findByUsername(user.getUsername());
        if(toLogin != null && toLogin.getPassword().equals(user.getPassword())){
            return toLogin;
        }
        return new User();
    }
}
