package de.tekup.associationspringboot.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String projectDescription;
    private String projectStatus;
    private String projectLeader;
    private double estimatedBudget;
    private double totalAmountSpent;
    private String duration;

    @OneToMany
    private List<RequestProject> requestProjects;

    @Column
    @CreationTimestamp
    private LocalDate createdAt;
    @Column
    @UpdateTimestamp
    private LocalDate updatedAt;

}
