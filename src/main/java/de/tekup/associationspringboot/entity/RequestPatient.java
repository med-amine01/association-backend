package de.tekup.associationspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPatient implements Serializable {

    /**
     * The RequestPatient entity has a composite primary key RequestPatientId,
     * which is embedded in the entity using the @EmbeddedId annotation.
     * The @MapsId annotation is used to map the requestId and patientId fields of the RequestPatientId to the Request and patient entities, respectively.
     * The @JoinColumn annotation specifies the column names in the join table.
     */
    @EmbeddedId
    private RequestPatientId id;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @MapsId("requestId")
    @JoinColumn(name = "request_id")
    private Request request;

    //amount received by funder if the request is accepted
    private double ReceivedAmount;

}

