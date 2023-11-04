package com.example.smallbankApp.dto;

import com.example.smallbankApp.model.transactions.Debit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponse {
    private String accountName;
    private String accountNumber;
    private Long amountTransferred;
    private Long senderBeforeBalance;
    private Long senderUpdatedBalance;
}
