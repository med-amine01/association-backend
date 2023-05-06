package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Account;
import de.tekup.associationspringboot.entity.TransactionHistory;
import de.tekup.associationspringboot.enums.TransactionType;
import de.tekup.associationspringboot.repository.AccountRepository;
import de.tekup.associationspringboot.repository.TransactionHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;
    private TransactionHistoryRepository historyRepository;


    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No account with ID " +id));
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accountRepository.findAll());
    }


    //ajouter fi solde
    public void deposit(Account account, double amount) {
        Account fetchedAccount = getAccount(account.getId());
        double newCurrentBalance = fetchedAccount.getCurrentBalance() + amount;
        double newTotalBalance = fetchedAccount.getCurrentBalance() + amount;

        fetchedAccount.setCurrentBalance(newCurrentBalance);
        fetchedAccount.setTotalBalance(newTotalBalance);

        TransactionHistory th = new TransactionHistory();
        th.setAccount(fetchedAccount);
        th.setAmount(amount);
        th.setTransactionType(TransactionType.DEPOSIT);

        //add transaction history to targeted account
        fetchedAccount.getTransactionHistories().add(th);
        historyRepository.save(th);

        accountRepository.save(fetchedAccount);
    }

    //retirer de solde
    public void withdraw(Account account, double amount) {
        Account fetchedAccount = getAccount(account.getId());
        double newCurrentBalance = fetchedAccount.getCurrentBalance() - amount;

        fetchedAccount.setCurrentBalance(newCurrentBalance);

        TransactionHistory th = new TransactionHistory();
        th.setAccount(fetchedAccount);
        th.setAmount(amount);
        th.setTransactionType(TransactionType.WITHDRAWAL);
        fetchedAccount.getTransactionHistories().add(th);
        historyRepository.save(th);

        accountRepository.save(fetchedAccount);
    }

}
