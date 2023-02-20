package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Patient;
import de.tekup.associationspringboot.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PatientService {
    private PatientRepository patientRepository;

    public Patient getPatient(Long id){
        return patientRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No patient with ID : " + id));
    }

    public List<Patient> getAllPatients(){
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;
    }

    public Patient addPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient){
        if(!patientRepository.existsById(patient.getId())){
            throw new NoSuchElementException("No patient with ID : " + patient.getId());
        }
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id){
        if(!patientRepository.existsById(id)){
            throw new NoSuchElementException("No patient with ID : " + id);
        }
        patientRepository.deleteById(id);
    }
}
