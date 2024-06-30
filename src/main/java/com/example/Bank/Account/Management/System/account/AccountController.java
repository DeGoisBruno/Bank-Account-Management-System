package com.example.Bank.Account.Management.System.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Retrieves all accounts
    @GetMapping("/list")
    public List<Account> getAccount() {
        return accountService.getAccount();
    }

    // Fetch an account by ID
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }

    // Creates a new account
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    // Transfer fund between two accounts
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(
            @RequestParam Long senderAccountId,
            @RequestParam Long receiverAccountId,
            @RequestParam double amount) {
        accountService.transferFunds(senderAccountId, receiverAccountId, amount);
        return ResponseEntity.ok("Funds transferred successfully");
    }

    // Deletes an account by ID
    @DeleteMapping(path = "/{accountId}/delete")
    public void deleteAccount(@PathVariable("accountId") Long id) {
        accountService.deleteAccount(id);
    }

    // Updates an account's name and/ balance
    @PutMapping(path = "/{accountId}/update")
    public void updateAccount(
        @PathVariable("accountId") Long accountId,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Double balance) {
        accountService.updateAccount(accountId, name, balance);
    }

    // Withdraw funds from an account
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdrawFunds(
            @PathVariable("accountId") Long accountId,
            @RequestParam double amount) {
        accountService.withdrawFunds(accountId, amount);
        return ResponseEntity.ok("Funds withdrawn successfully");
    }

    // Deposit funds into an account
    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> depositFunds(
            @PathVariable("accountId") Long accountId,
            @RequestParam double amount) {
        accountService.depositFunds(accountId, amount);
        return ResponseEntity.ok("Funds deposited successfully");
    }
}