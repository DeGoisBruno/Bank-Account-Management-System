package com.example.Bank.Account.Management.System.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public List<Account> getAccount() {
        return accountService.getAccount();
    }

    // GET endpoint to fetch an account by ID
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    @DeleteMapping(path = "/{accountId}/delete")
    public void deleteAccount(@PathVariable("accountId") Long id) {
        accountService.deleteAccount(id);
    }

    @PutMapping(path = "/{accountId}/update")
    public void updateAccount(
        @PathVariable("accountId") Long accountId,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Double balance) {
    accountService.updateAccount(accountId, name, balance);
    }
}