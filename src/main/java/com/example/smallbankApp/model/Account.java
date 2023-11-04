package com.example.smallbankApp.model;

import com.example.smallbankApp.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank
    public String account_name;
    @NotBlank
    public String accountNumber;
    @NotNull
    @Enumerated(EnumType.STRING)
    public AccountType accountType;
    public Long balance;
    @OneToOne
    private User user;

}
