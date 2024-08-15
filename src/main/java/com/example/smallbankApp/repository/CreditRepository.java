package com.example.smallbankApp.repository;

import com.example.smallbankApp.model.transactions.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByCreditAccountNumberOrderByDateDesc(String accountNumber);
}
