package com.example.smallbankApp.repository;

import com.example.smallbankApp.model.transactions.Debit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitRepository extends JpaRepository<Debit, Long> {
}
