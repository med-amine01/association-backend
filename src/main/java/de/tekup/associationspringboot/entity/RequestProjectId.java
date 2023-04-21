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
public class RequestProjectId implements Serializable {
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "project_id")
    private Long projectId;
}
