package com.example.smallbankApp.repository;

import com.example.smallbankApp.model.transactions.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
