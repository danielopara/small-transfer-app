package com.example.smallbankApp.service.user;

import com.example.smallbankApp.exceptions.EmailExistsException;
import com.example.smallbankApp.exceptions.PhoneNumberExistsException;
import com.example.smallbankApp.model.User;

import java.util.List;

public interface UserService {

    User createUserAndAccount(User user) throws EmailExistsException, PhoneNumberExistsException;
    List<User> getAllUsers();
}
