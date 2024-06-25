//package com.example.Bank.Account.Management.System.account;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class AccountConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(AccountRepository repository) {
//        return args -> {
//            // Generate unique account numbers for each account
//            String mikeAccountNumber = AccountNumberGenerator.generateAccountNumber();
//            while (repository.findAccountByAccountNumber(mikeAccountNumber).isPresent()) {
//                mikeAccountNumber = AccountNumberGenerator.generateAccountNumber();
//            }
//
//            String steveAccountNumber = AccountNumberGenerator.generateAccountNumber();
//            while (repository.findAccountByAccountNumber(steveAccountNumber).isPresent()) {
//                steveAccountNumber = AccountNumberGenerator.generateAccountNumber();
//            }
//
//            Account mike = new Account(
//                    mikeAccountNumber,
//                    "Mike Bordin",
//                    19.90
//            );
//
//            Account steve = new Account(
//                    steveAccountNumber,
//                    "Steve Jordan",
//                    23.75
//            );
//
//            // Save accounts to the repository
//            repository.saveAll(List.of(mike, steve));
//        };
//    }
//}


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
            String mikeAccountNumber = generateUniqueAccountNumber(repository);
            String steveAccountNumber = generateUniqueAccountNumber(repository);

            Account mike = new Account(
                    mikeAccountNumber,
                    "Mike Bordin",
                    19.90
            );

            Account steve = new Account(
                    steveAccountNumber,
                    "Steve Jordan",
                    23.75
            );

            // Save accounts to the repository
            repository.saveAll(List.of(mike, steve));
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
