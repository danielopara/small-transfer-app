package com.example.smallbankApp.service.user;

import com.example.smallbankApp.exceptions.EmailExistsException;
import com.example.smallbankApp.exceptions.PhoneNumberExistsException;
import com.example.smallbankApp.model.Account;
import com.example.smallbankApp.model.User;
import com.example.smallbankApp.repository.AccountRepository;
import com.example.smallbankApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    private String generateRandomAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumberBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            accountNumberBuilder.append(random.nextInt(10));
        }
        return accountNumberBuilder.toString();
    }

    @Override
    public User createUserAndAccount(User user) throws EmailExistsException, PhoneNumberExistsException {
        if(userRepository.existsByEmail(user.getEmail())){
                throw new EmailExistsException("email already exists");
        }
        if(userRepository.existsByPhoneNumber(user.getPhoneNumber())){
            throw new PhoneNumberExistsException("phone number already exists");
        }

        User createUser = User.builder()
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .email(user.getEmail())
                .dob(user.getDob())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .build();
        createUser = userRepository.save(createUser);

        Account account = Account.builder()
                .user(createUser)
                        .account_name(user.getFirst_name()+ " " + user.getLast_name())
                                .accountNumber(generateRandomAccountNumber())
                                        .accountType(user.getAccountType())
                                                .balance(1000L)
                                                        .build();
        accountRepository.save(account);

        return createUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
