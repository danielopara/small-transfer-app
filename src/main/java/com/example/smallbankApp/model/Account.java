package com.example.smallbankApp.model;

import com.example.smallbankApp.enums.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String account_name;
    @NotBlank
    private String accountNumber;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Long balance;
    @OneToOne
    private User user;

}
