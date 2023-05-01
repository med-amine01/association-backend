package de.tekup.associationspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.tekup.associationspringboot.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @JsonIgnore
    @ManyToOne
    private Account account;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime transactionDate;

    private double amount;

}
