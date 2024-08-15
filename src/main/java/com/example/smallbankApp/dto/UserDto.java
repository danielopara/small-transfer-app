package com.example.smallbankApp.dto;

import com.example.smallbankApp.enums.AccountType;
import com.example.smallbankApp.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    public String first_name;
    public String last_name;

    public String email;

    public String phoneNumber;
    @Enumerated(EnumType.STRING)
    public Gender gender;

    public LocalDate dob;
    //    @JsonIgnore
    public AccountType accountType;
}
