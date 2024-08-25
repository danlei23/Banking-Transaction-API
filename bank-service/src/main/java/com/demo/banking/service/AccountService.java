package com.demo.banking.service;

import com.demo.banking.dto.AccountDTO;
import com.demo.banking.exception.AccountNotFoundException;
import com.demo.banking.model.Account;
import com.demo.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setName((accountDTO.getName()));
        account.setBalance(accountDTO.getInitialBalance());

        Account savedAccount = accountRepository.save(account);

        return new AccountDTO(savedAccount.getId(), savedAccount.getName(), savedAccount.getBalance());
    }

    public AccountDTO getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return new AccountDTO(account.getId(), account.getName(), account.getBalance());
    }
}
