package com.example.Bank.Account.Management.System.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface AccountRepository
        extends JpaRepository<Account, Long> {

    @Query("SELECT s FROM Account s WHERE s.accountNumber = ?1")
    Optional<Account> findAccountByAccountNumber (String accountNumber);
    }

