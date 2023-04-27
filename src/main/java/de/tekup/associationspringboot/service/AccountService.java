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
    public void Deposit(Account account, double amount) {
        List<TransactionHistory> thList;
        thList = historyRepository.findAllByAccount_Id(account.getId());

        double money = 0;
        for(int i=0; i<thList.size(); i++) {
            money += thList.get(i).getAmount();
        }
        money += amount;
        account.setCurrentBalance(money);
        account.setTotalBalance(money);

        TransactionHistory th = new TransactionHistory();
        th.setAccount(account);
        th.setAmount(amount);
        th.setTransactionType(TransactionType.DEPOSIT);
        thList.add(th);

        historyRepository.save(th);
        account.setTransactionHistories(thList);

        accountRepository.save(account);
    }

    //retirer de solde
    public void withdraw(Account account, double amount) {
        List<TransactionHistory> thList = new ArrayList<>();
        Account acc = accountRepository.findById(account.getId()).get();

        double money = acc.getCurrentBalance() - amount;
        account.setCurrentBalance(money);

        double total = 0;
        List<TransactionHistory> depositTypeTransactions = historyRepository.findAllByTransactionType(TransactionType.DEPOSIT);
        for(int i=0; i<depositTypeTransactions.size(); i++) {
            total += depositTypeTransactions.get(i).getAmount();
        }
        account.setTotalBalance(Math.round(total));

        TransactionHistory th = new TransactionHistory();
        th.setAccount(account);
        th.setAmount(amount);
        th.setTransactionType(TransactionType.WITHDRAWAL);
        thList.add(th);

        historyRepository.save(th);
        account.setTransactionHistories(thList);

        accountRepository.save(account);
    }

}
