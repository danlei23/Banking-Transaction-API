package com.demo.banking.repository;

import com.demo.banking.model.Account;
import com.demo.banking.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    public Transaction save(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findBySenderOrReceiver(Account sender, Account receiver) {
        return transactions.stream()
                .filter(transaction -> transaction.getSender().equals(sender) ||transaction.getReceiver().equals(receiver))
                .collect(Collectors.toList());
    }
}
