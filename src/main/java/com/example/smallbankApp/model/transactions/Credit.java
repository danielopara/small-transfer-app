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
@Table(name = "credit_table")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String creditAccountName;
    @NotBlank
    public String creditAccountNumber;
    @NotNull
    public Long amount;
    @NotNull
    public Long balanceBefore;
    @NotNull
    public Long balanceAfter;
    public String description;

    public Date date;
    @OneToOne
    @JoinColumn(name = "debit_id")
    private Debit debit;
}
