package de.tekup.associationspringboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pName;
    private String pNumber;
    private String pDetails;
    private String healthStatus;
    private double fundingNeeded;
}
