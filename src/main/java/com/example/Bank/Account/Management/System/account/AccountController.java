package com.example.Bank.Account.Management.System.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {


    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccount() {
        return accountService.getAccount();
    }

    @PostMapping
    public void createNewAccount(@RequestBody Account account) {
        accountService.addNewAccount(account);
    }

    @DeleteMapping(path = "{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long id) {
        accountService.deleteAccount(id);
    }

    @PutMapping(path = "{accountId}")
    public void updateAccount(
        @PathVariable("accountId") Long accountId,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) double balance) {
    accountService.updateAccount(accountId, name, balance);


    }
}