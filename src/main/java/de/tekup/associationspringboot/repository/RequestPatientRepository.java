package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.Patient;
import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.entity.RequestPatient;
import de.tekup.associationspringboot.entity.RequestPatientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestPatientRepository extends JpaRepository<RequestPatient, RequestPatientId> {

    //we will return all data related to request
    List<RequestPatient> findAllByRequest(Request id);
}
