package com.example.smallbankApp.controller;

import com.example.smallbankApp.dto.TransactionRequest;
import com.example.smallbankApp.dto.TransactionResponse;
import com.example.smallbankApp.exceptions.InsufficientBalanceException;
import com.example.smallbankApp.model.Account;
import com.example.smallbankApp.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/allAccounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/get-account")
    public ResponseEntity<String> getAccountName(@RequestParam String accountNumber){
        Optional<Account> findAccount = accountService.findByAccountNumber(accountNumber);
        if (findAccount.isPresent()) {
//            return ResponseEntity.ok(findAccount.get().account_name);
            return ResponseEntity.status(HttpStatus.OK).body(findAccount.get().account_name);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transferFunds(@RequestBody TransactionRequest request) throws InsufficientBalanceException, AccountNotFoundException{

            TransactionResponse response = accountService.transferMoney(
                    request.getSenderAccountNumber(),
                    request.getReceiverAccountNumber(),
                    request.getAmountTransferred());

            return ResponseEntity.ok(response);
    }
}

