package de.tekup.associationspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionId implements Serializable {

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "account_id")
    private Long accountId;
}
