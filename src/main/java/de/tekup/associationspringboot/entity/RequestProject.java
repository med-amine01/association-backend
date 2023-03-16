package de.tekup.associationspringboot.entity;

import javax.persistence.*;

@Entity
public class RequestProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double fundingAmount;
    @ManyToOne
    private Request request;
    @ManyToOne
    private Project project;
}
