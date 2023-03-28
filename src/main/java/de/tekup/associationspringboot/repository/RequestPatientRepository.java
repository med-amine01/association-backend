package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.entity.RequestPatient;
import de.tekup.associationspringboot.entity.RequestPatientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@Repository
@CrossOrigin("http://localhost:4200")
public interface RequestPatientRepository extends JpaRepository<RequestPatient, RequestPatientId> {

    //we will return all data related to request
    List<RequestPatient> findAllByRequest(Request id);
}
