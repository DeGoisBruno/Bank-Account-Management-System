package com.example.Bank.Account.Management.System.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccount() {
        return accountRepository.findAll();
    }

    public void addNewAccount(Account account) {
        Optional<Account> accountOptional = accountRepository
        .findAccountByName(account.getName());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("Name Exists");
        }
        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.findById(accountId);
        boolean exists = accountRepository.existsById(accountId);
        if (!exists) {
            throw new IllegalStateException("account with id " + accountId + " does not exists");
        }
        accountRepository.deleteById(accountId);
    }

    @Transactional
    public void updateAccount(Long id, String name, double balance) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "account with id " + id + " does not exist"));
                if (name != null && !name.isEmpty() &&
                !Objects.equals(account.getName(), name)) {
                    account.setName(name);
                }
        if (balance >= 0 && account.getBalance() != balance) {
            account.setBalance(balance);
        }
    }
}
