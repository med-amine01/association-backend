package de.tekup.associationspringboot.entity;

import de.tekup.associationspringboot.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistory implements Serializable {

    @EmbeddedId
    private TransactionId id;

    @ManyToOne
    @MapsId("userEmail")
    @JoinColumn(name = "user_email")
    private User funder;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private  Account account;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime transactionDate;

    private double amount;

}
