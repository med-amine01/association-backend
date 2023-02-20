package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient,Long> {
}
