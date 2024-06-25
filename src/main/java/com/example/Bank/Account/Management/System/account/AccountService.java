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
        .findAccountByAccountNumber(account.getAccountNumber());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("Account with account number " + account.getAccountNumber() + " already exists");
        }
        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.findById(accountId);
        boolean exists = accountRepository.existsById(accountId);
        if (!exists) {
            throw new IllegalStateException("Account with id " + accountId + " does not exists");
        }
        accountRepository.deleteById(accountId);
    }

    public Account createAccount(Account account) {
        String accountNumber = generateUniqueAccountNumber();
        account.setAccountNumber(accountNumber);
        return accountRepository.save(account);
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = AccountNumberGenerator.generateAccountNumber();
        } while (accountRepository.findAccountByAccountNumber(accountNumber).isPresent());
        return accountNumber;
    }


    @Transactional
    public void updateAccount(Long id, String name, double balance) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Account with id " + id + " does not exist"));
                if (name != null && !name.isEmpty() &&
                !Objects.equals(account.getName(), name)) {
                    account.setName(name);
                }
        if (balance >= 0 && account.getBalance() != balance) {
            account.setBalance(balance);
        }
    }
}
