package com.example.smallbankApp.model;

import com.example.smallbankApp.enums.AccountType;
import com.example.smallbankApp.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank
    public String first_name;
    @NotBlank
    public String last_name;
    @NotBlank
    @Email
    public String email;
    @NotBlank
    @Size(min = 11, max = 11, message = "Phone number must be 11 characters long")
    @Pattern(regexp = "^[0-9]*$", message = "Phone number must contain only numeric characters")
    public String phoneNumber;
    @NotNull
    @Enumerated(EnumType.STRING)
    public Gender gender;
    @NotNull
    public LocalDate dob;
//    @JsonIgnore
    public AccountType accountType;

}

