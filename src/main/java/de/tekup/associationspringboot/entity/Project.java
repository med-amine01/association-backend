package de.tekup.associationspringboot.entity;

import lombok.Data;

import javax.persistence.*;

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

}
