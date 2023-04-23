package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Account;
import de.tekup.associationspringboot.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;
    private TransactionHistoryService historyService;

    public void DepositMoney() {

    }

}
