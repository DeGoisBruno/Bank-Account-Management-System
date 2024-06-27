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

    //Retrieves accounts from the repository
    public List<Account> getAccount() {
        return accountRepository.findAll();
    }

    //Add a new a account to the repository
    public void addNewAccount(Account account) {
        Optional<Account> accountOptional = accountRepository
        .findAccountByAccountNumber(account.getAccountNumber());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("Account with account number " + account.getAccountNumber() + " already exists");
        }
        accountRepository.save(account);
    }

    //Deletes an account from the repository by ID
    public void deleteAccount(Long accountId) {
        accountRepository.findById(accountId);
        boolean exists = accountRepository.existsById(accountId);
        if (!exists) {
            throw new IllegalStateException("Account with id " + accountId + " does not exists");
        }
        accountRepository.deleteById(accountId);
    }

    //Create a new account with a unique account number
    public Account createAccount(Account account) {
        String accountNumber = generateUniqueAccountNumber();
        account.setAccountNumber(accountNumber);
        return accountRepository.save(account);
    }

    //Generates a unique account number by ensuring it doesn't already exists in the repository
    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = AccountNumberGenerator.generateAccountNumber();
        } while (accountRepository.findAccountByAccountNumber(accountNumber).isPresent());
        return accountNumber;
    }

    // Retrieves an account from the repository by ID
    @Transactional
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Account with id " + id + " does not exist"));
    }

    //Updates the name and balance of an account
    //If the name or balance provided are valid and different from the existing values, they are updated
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
