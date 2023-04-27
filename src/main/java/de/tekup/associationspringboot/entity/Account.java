package de.tekup.associationspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User funder;

    @OneToMany
    private List<TransactionHistory> transactionHistories;

    //solde courant
    private double currentBalance;

    //solde totale
    private double totalBalance;

    private boolean enable;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;
}
