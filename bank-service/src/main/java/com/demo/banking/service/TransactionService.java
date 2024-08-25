package com.demo.banking.service;

import com.demo.banking.dto.TransactionDTO;
import com.demo.banking.exception.AccountNotFoundException;
import com.demo.banking.exception.InsufficientFundsException;
import com.demo.banking.model.Account;
import com.demo.banking.model.Transaction;
import com.demo.banking.repository.AccountRepository;
import com.demo.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void transferFunds(TransactionDTO transactionDTO) {
        Account sender = accountRepository.findById(transactionDTO.getSenderId())
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found"));
        Account receiver = accountRepository.findById(transactionDTO.getReceiverId())
                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found"));

        if (sender.getBalance() < transactionDTO.getAmount()) {
            throw new InsufficientFundsException("Insufficient funds in sender's account");
        }
        sender.setBalance(sender.getBalance() - transactionDTO.getAmount());
        receiver.setBalance(receiver.getBalance() + transactionDTO.getAmount());

        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transaction transaction = new Transaction(sender, receiver, transactionDTO.getAmount());
        transactionRepository.save(transaction);
    }

    public List<TransactionDTO> getTransactionHistory(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        List<Transaction> transactions = transactionRepository.findBySenderOrReceiver(account, account);

        return transactions.stream().map(transaction -> new TransactionDTO(
                transaction.getSender().getId(),
                transaction.getReceiver().getId(),
                transaction.getAmount(),
                transaction.getTimestamp()
        )).collect(Collectors.toList());
    }
}
