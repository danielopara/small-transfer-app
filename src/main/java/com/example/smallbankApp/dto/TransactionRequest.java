package com.example.smallbankApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private Long amountTransferred;
}
