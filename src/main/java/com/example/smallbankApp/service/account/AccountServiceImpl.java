package com.example.smallbankApp.service.account;

import com.example.smallbankApp.dto.TransactionResponse;
import com.example.smallbankApp.exceptions.InsufficientBalanceException;
import com.example.smallbankApp.model.Account;
import com.example.smallbankApp.model.transactions.Credit;
import com.example.smallbankApp.model.transactions.Debit;
import com.example.smallbankApp.repository.AccountRepository;
import com.example.smallbankApp.repository.CreditRepository;
import com.example.smallbankApp.repository.DebitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private DebitRepository debitRepository;
    //models
//    Credit credit = new Credit();
//    Debit debit = new Debit();
    public Boolean checkSufficientBalance(String senderAccountNumber, Long amount){
        Optional<Account> senderAccountOpt = accountRepository.findByAccountNumber(senderAccountNumber);
        return senderAccountOpt.map(senderAccount -> senderAccount.getBalance().compareTo(amount) >= 0).orElse(false);
    }
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Transactional
    public TransactionResponse transferMoney(String senderAccountNumber,
                                             String receiverAccountNumber,
                                             Long moneyTransferred) throws AccountNotFoundException {
        Optional<Account> findSenderAccount = accountRepository.findByAccountNumber(senderAccountNumber);
        Optional<Account> findReceiverAccount = accountRepository.findByAccountNumber(receiverAccountNumber);

        if (findSenderAccount.isPresent() && findReceiverAccount.isPresent()) {
            Account senderAccount = findSenderAccount.get();
            Account receiverAccount = findReceiverAccount.get();

            if (checkSufficientBalance(senderAccountNumber, moneyTransferred)) {
                // getting the before balance
                Long senderBeforeBalance = senderAccount.getBalance();
                Long receiverBeforeBalance = receiverAccount.getBalance();

                senderAccount.setBalance(senderAccount.getBalance() - moneyTransferred);
                receiverAccount.setBalance(receiverAccount.getBalance() + moneyTransferred);
                accountRepository.save(senderAccount);
                accountRepository.save(receiverAccount);

                //saving in both credit and debit tables
                Debit debit;
                Credit credit;
                credit = Credit.builder()
                        .amount(moneyTransferred)
                        .creditAccountName(receiverAccount.getAccountNumber())
                        .creditAccountNumber(receiverAccount.getAccount_name())
                        .date(new Date())
                        .balanceAfter(receiverAccount.getBalance())
                        .balanceBefore(receiverBeforeBalance)
                        .description("First transaction for credit")
                        .build();

                debit = Debit.builder()
                        .amount(moneyTransferred)
                        .balanceAfter(senderAccount.getBalance())
                        .balanceBefore(senderBeforeBalance)
                        .description("For transaction for debit")
                        .date(new Date())
                        .debitAccountName(senderAccount.getAccount_name())
                        .debitAccountNumber(senderAccount.getAccountNumber())
                        .build();

                debit.setCredit(credit);
                credit.setDebit(debit);
                creditRepository.save(credit);
                debitRepository.save(debit);

                return new TransactionResponse(senderAccount.getAccount_name(),
                        senderAccount.getAccountNumber(),
                        moneyTransferred,
                        senderBeforeBalance,
                        senderAccount.getBalance());
            } else {
                throw new InsufficientBalanceException("Insufficient balance in the sender's account.");
            }
        }else {
            throw new AccountNotFoundException("Sender or recipient account number does not exist");
        }
    }
}
