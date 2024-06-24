package com.example.Bank.Account.Management.System.account;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccountConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            AccountRepository repository) {
        return args -> {
            Account mike = new Account(
                    "Mike Bordin",
                    19.90
            );
            Account steve = new Account(
                    "Steve Jordan",
                    23.75
            );
            repository.saveAll(List.of(mike, steve));
        };

    }

}
