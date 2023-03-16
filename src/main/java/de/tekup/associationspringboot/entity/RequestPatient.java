package de.tekup.associationspringboot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RequestPatient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //amount received by funder if the request is accepted
    private double ReceivedAmount;
    @ManyToOne
    private Request request;
    @ManyToOne
    private Patient patient;
}
