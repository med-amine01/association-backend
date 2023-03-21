package de.tekup.associationspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    //amount requested by funder
    private double requestedAmount;
    @ManyToOne
    private User funder;

    @OneToMany
    public List<RequestPatient> requestPatients;

    //this is the list of selected patients
    @Transient
    private List<Patient> patients;

    @Column
    @CreationTimestamp
    private LocalDate createdAt;
    @Column
    @UpdateTimestamp
    private LocalDate updatedAt;
}
