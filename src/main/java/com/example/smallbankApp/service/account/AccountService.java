package com.example.smallbankApp.service.account;

import com.example.smallbankApp.dto.TransactionResponse;
import com.example.smallbankApp.model.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccounts();
    Optional<Account> findByAccountNumber(String accountNumber);
    TransactionResponse transferMoney(String senderAccountNumber,
                                      String receiverAccountNumber,
                                      Long moneyTransferred) throws AccountNotFoundException;
}
