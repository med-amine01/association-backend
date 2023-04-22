package de.tekup.associationspringboot.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pName;
    private String pNumber;
    private String pAddress;
    private String healthStatus;

    //amount needed
    private double fundingNeeded;

    @Column
    @CreationTimestamp
    private LocalDate createdAt;
    @Column
    @UpdateTimestamp
    private LocalDate updatedAt;
}
