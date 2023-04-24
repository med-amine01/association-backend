package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.Account;
import de.tekup.associationspringboot.entity.TransactionHistory;
import de.tekup.associationspringboot.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    List<TransactionHistory> findAllByAccount_Id(Long accountId);

    List<TransactionHistory> findAllByTransactionType(TransactionType transactionType);
}
