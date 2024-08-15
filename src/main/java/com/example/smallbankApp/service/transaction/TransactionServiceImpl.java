package com.example.smallbankApp.service.transaction;

import com.example.smallbankApp.model.transactions.Credit;
import com.example.smallbankApp.model.transactions.Debit;
import com.example.smallbankApp.repository.CreditRepository;
import com.example.smallbankApp.repository.DebitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private DebitRepository debitRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Map<String, String> getCurrentTransactions(String senderAccountNumber, String receiverAccountNumber) {
        Map<String, String> transactionStatuses = new HashMap<>();

        // Get credits and debits for sender account
        List<Credit> senderCredits = creditRepository.findByCreditAccountNumberOrderByDateDesc(senderAccountNumber);
        List<Debit> senderDebits = debitRepository.findByDebitAccountNumberOrderByDateDesc(senderAccountNumber);

        // Determine status for sender account
        if (!senderCredits.isEmpty()) {
            transactionStatuses.put(senderAccountNumber, "credited");
        } else if (!senderDebits.isEmpty()) {
            transactionStatuses.put(senderAccountNumber, "debited");
        } else {
            transactionStatuses.put(senderAccountNumber, "none");
        }

        // Get credits and debits for receiver account
        List<Credit> receiverCredits = creditRepository.findByCreditAccountNumberOrderByDateDesc(receiverAccountNumber);
        List<Debit> receiverDebits = debitRepository.findByDebitAccountNumberOrderByDateDesc(receiverAccountNumber);

        // Determine status for receiver account
        if (!receiverCredits.isEmpty()) {
            transactionStatuses.put(receiverAccountNumber, "credited");
        } else if (!receiverDebits.isEmpty()) {
            transactionStatuses.put(receiverAccountNumber, "debited");
        } else {
            transactionStatuses.put(receiverAccountNumber, "none");
        }

        return transactionStatuses;
    }

}
