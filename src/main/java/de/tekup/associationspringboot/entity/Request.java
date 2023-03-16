package de.tekup.associationspringboot.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    //amount requested by funder
    private double requestedAmount;
    @OneToMany
    private List<RequestPatient> requestPatients;
    @ManyToOne
    private User funder;

    //this is the list of select patients to be funded
    @OneToMany
    private List<Patient> patients;
    @Column
    @CreationTimestamp
    private LocalDate createdAt;
    @Column
    @UpdateTimestamp
    private LocalDate updatedAt;
}
