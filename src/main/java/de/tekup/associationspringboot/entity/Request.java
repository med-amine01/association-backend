package de.tekup.associationspringboot.entity;

import de.tekup.associationspringboot.enums.RequestStatus;
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

    private String requestName;
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestToFunderStatus;

    //amount requested by funder
    private double requestedAmount;

    @ManyToOne
    private User funder;

    @ManyToMany
    private List<Patient> patient;

    //PROJECT
    @OneToMany
    private List<RequestProject> requestProjects;
    @ManyToMany
    private List<Project> projects;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}
