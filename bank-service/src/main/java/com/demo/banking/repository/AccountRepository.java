package com.demo.banking.repository;

import com.demo.banking.model.Account;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccountRepository {
    private final Map<Long, Account> accounts = new HashMap<>();
    private Long nextId = 1L;

    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public Account save(Account account) {
        if (account.getId() == null) {
            account.setId(nextId++);
        }
        accounts.put(account.getId(), account);
        return account;
    }

    public boolean existsById(Long id) {
        return accounts.containsKey(id);
    }
}
