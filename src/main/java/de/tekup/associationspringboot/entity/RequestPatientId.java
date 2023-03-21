package de.tekup.associationspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPatientId implements Serializable {

    @Column(name = "request_id")
    private Long requestId;
    @Column(name = "patient_id")
    private Long patientId;

}
