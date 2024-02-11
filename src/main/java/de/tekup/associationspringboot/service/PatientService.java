package de.tekup.associationspringboot.service;

import com.github.javafaker.Faker;
import de.tekup.associationspringboot.entity.Patient;
import de.tekup.associationspringboot.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PatientService {
    private PatientRepository patientRepository;


    //GENERATING FAKE DATA FOR PATIENT
    @PostConstruct
    public Iterable<Patient> generateData() {
        Faker faker = new Faker();
        List<Patient> patientList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Patient p = new Patient();
            p.setPName(faker.name().fullName());
            p.setPNumber(faker.phoneNumber().phoneNumber());
            p.setPAddress(faker.address().fullAddress());
            p.setHealthStatus("Emergency");
            p.setFundingNeeded(faker.number().randomDouble(2, 150, 9999));
            patientList.add(p);
        }
        return patientRepository.saveAll(patientList);
    }

    public Patient getPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No patient with ID : " + id));
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient) {
        if (!patientRepository.existsById(patient.getId())) {
            throw new NoSuchElementException("No patient with ID : " + patient.getId());
        }
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new NoSuchElementException("No patient with ID : " + id);
        }
        patientRepository.deleteById(id);
    }
}
