package com.example.smallbankApp.controller;

import com.example.smallbankApp.exceptions.EmailExistsException;
import com.example.smallbankApp.exceptions.PhoneNumberExistsException;
import com.example.smallbankApp.model.User;
import com.example.smallbankApp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) throws PhoneNumberExistsException, EmailExistsException {
        return new ResponseEntity<User>(userService.createUserAndAccount(user), HttpStatus.CREATED);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
