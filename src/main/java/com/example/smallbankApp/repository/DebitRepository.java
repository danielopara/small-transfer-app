package com.example.smallbankApp.repository;

import com.example.smallbankApp.model.transactions.Debit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebitRepository extends JpaRepository<Debit, Long> {
    List<Debit> findByDebitAccountNumberOrderByDateDesc(String accountNumber);}
