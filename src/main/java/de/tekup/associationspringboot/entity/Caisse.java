package de.tekup.associationspringboot.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Caisse {

    private static final Caisse instance = new Caisse();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;

    public static Caisse getInstance() {
        return instance;
    }

    public Long getId() {
        return id;
    }

    public synchronized void ajouter(double montant) {
        amount += montant;
    }

    public synchronized void retirer(double montant) {
        if (montant < this.amount) {
            amount -= montant;
        }
    }

    public synchronized double getSolde() {
        return amount;
    }

}
