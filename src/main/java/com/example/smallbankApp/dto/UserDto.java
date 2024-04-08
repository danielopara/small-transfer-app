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

    @Size(min = 11, max = 11, message = "Phone number must be 11 characters long")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must contain only numeric characters")
    public String phoneNumber;
    @NotNull
    @Enumerated(EnumType.STRING)
    public Gender gender;

    public LocalDate dob;
    //    @JsonIgnore
    @Enumerated(EnumType.STRING)
    public AccountType accountType;
}
