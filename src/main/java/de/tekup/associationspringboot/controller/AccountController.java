package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Account;
import de.tekup.associationspringboot.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor

public class AccountController {
    private AccountService accountService;

    @GetMapping("/getall")
    public List<Account> getAll() {
        return accountService.getAllAccounts();
    }
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable("id") Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping("/deposit")
    public void depositMoney(@RequestBody Account account) {
        double amount = account.getTransactionHistories().get(0).getAmount();
        accountService.Deposit(account,amount);
    }


    @PostMapping("/withdraw")
    public void withdrawMoney(@RequestBody Account account) {
        double amount = account.getTransactionHistories().get(0).getAmount();
        accountService.withdraw(account,amount);
    }
}
