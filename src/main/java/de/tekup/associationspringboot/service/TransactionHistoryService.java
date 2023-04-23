package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Account;
import de.tekup.associationspringboot.entity.TransactionHistory;
import de.tekup.associationspringboot.entity.TransactionId;
import de.tekup.associationspringboot.entity.User;
import de.tekup.associationspringboot.enums.TransactionType;
import de.tekup.associationspringboot.repository.TransactionHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionHistoryService {
    private TransactionHistoryRepository historyRepository;


    public void addTransactionHistory(User funder,
                                      Account account,
                                      TransactionType type,
                                      double amount) {

        TransactionHistory th = new TransactionHistory();
        //our id (TransactionId) is composed by two keys
        th.setId(new TransactionId(funder.getUserEmail() , account.getId()));
        th.setFunder(funder);
        th.setAccount(account);
        th.setTransactionType(type);
        th.setAmount(amount);

        historyRepository.save(th);
    }
}
