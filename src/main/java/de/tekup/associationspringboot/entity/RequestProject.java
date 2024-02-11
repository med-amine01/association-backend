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
public class RequestProject implements Serializable {
    @EmbeddedId
    private RequestProjectId id;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;


    @ManyToOne
    @MapsId("requestId")
    @JoinColumn(name = "request_id")
    private Request request;

    //amount received (versed to the project) by funder if the request is accepted
    private double ReceivedAmount;
}
