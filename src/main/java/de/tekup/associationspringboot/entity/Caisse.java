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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private static final   Caisse instance=new Caisse();

    public Long getId() {
        return id;
    }

    //badelt hnee
    public static Caisse getInstance() {
        return instance;
    }

    public synchronized void ajouter(double montant) {
        amount += montant;
    }

    public synchronized void retirer(double montant) {
        if (montant<this.amount){
        amount -= montant;}
    }

    public synchronized double getSolde() {
        return amount;
    }



    // getters, setters, etc.


}
