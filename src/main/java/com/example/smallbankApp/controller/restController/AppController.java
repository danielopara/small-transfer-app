package com.example.smallbankApp.controller.restController;

import com.example.smallbankApp.dto.BaseResponse;
import com.example.smallbankApp.dto.TransactionResponse;
import com.example.smallbankApp.dto.UserDto;
import com.example.smallbankApp.service.account.AccountServiceImpl;
import com.example.smallbankApp.service.transaction.TransactionServiceImpl;
import com.example.smallbankApp.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AppController {
    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String senderAccountNumber,
                           @RequestParam String receiverAccountNumber,
                           @RequestParam Long amount,
                           Model model) {
        try {
            TransactionResponse response = accountService.transferMoney(senderAccountNumber, receiverAccountNumber, amount);
            model.addAttribute("transactionResponse", response);

            Map<String, String> transactionStatuses = transactionService.getCurrentTransactions(senderAccountNumber, receiverAccountNumber);

            model.addAttribute("transactionStatuses", transactionStatuses);
            model.addAttribute("accounts", accountService.getAllAccounts());
            model.addAttribute("users", userService.getAllUsers());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index";
    }

    @PostMapping("/createUser")
    public String createUser(UserDto userDto, Model model) {
        try {
            BaseResponse response = userService.createUserAndAccount(userDto);
            model.addAttribute("userCreationResponse", response);

            model.addAttribute("accounts", accountService.getAllAccounts());
            model.addAttribute("users", userService.getAllUsers());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("userDto", userDto);
        }
        return "index";
    }
}
