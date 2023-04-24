package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Account;
import de.tekup.associationspringboot.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor

public class AccountController {
    private AccountService accountService;

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
