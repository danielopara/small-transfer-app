package com.example.smallbankApp.service.transaction;

import java.util.Map;

public interface TransactionService {
    Map<String, String> getCurrentTransactions(String senderAccountNumber, String receiverAccountNumber);
}
