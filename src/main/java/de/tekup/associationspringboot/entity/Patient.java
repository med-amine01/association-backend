package de.tekup.associationspringboot.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToMany
    private List<RequestPatient> requestPatients;

    @Column
    @CreationTimestamp
    private LocalDate createdAt;
    @Column
    @UpdateTimestamp
    private LocalDate updatedAt;
}
