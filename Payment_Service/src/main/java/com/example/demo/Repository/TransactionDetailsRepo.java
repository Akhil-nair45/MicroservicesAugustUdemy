package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.TransactionDetails;

@Repository
public interface TransactionDetailsRepo extends JpaRepository<TransactionDetails,Long> {

}
