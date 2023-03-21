package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.RequestPatient;
import de.tekup.associationspringboot.entity.RequestPatientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestPatientRepository extends JpaRepository<RequestPatient, RequestPatientId> {
}
