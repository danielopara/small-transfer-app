package com.example.smallbankApp.model.transactions;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "debit_table")
public class Debit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String debitAccountName;
    @NotNull
    public String debitAccountNumber;
    @NotNull
    public Long amount;
    @NotNull
    public Long balanceBefore;
    @NotNull
    public Long balanceAfter;
    public String description;
    public Date date;
    @OneToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;
}
