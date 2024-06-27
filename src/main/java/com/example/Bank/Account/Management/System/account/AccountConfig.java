package com.example.Bank.Account.Management.System.account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository repository) {
        return args -> {
            // Generate unique account numbers for each account
            String thomasAccountNumber = generateUniqueAccountNumber(repository);
            String julieAccountNumber = generateUniqueAccountNumber(repository);

            Account thomas = new Account(
                    thomasAccountNumber,
                    "Thomas Hedlund",
                    19.90
            );

            Account julie = new Account(
                    julieAccountNumber,
                    "Julie Christmas",
                    23.75
            );

            // Save accounts to the repository
            repository.saveAll(List.of(thomas, julie));
        };
    }

    private String generateUniqueAccountNumber(AccountRepository repository) {
        String accountNumber;
        do {
            accountNumber = AccountNumberGenerator.generateAccountNumber();
        } while (repository.findAccountByAccountNumber(accountNumber).isPresent());
        return accountNumber;
    }
}
