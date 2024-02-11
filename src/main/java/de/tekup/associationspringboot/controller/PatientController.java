package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Patient;
import de.tekup.associationspringboot.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@AllArgsConstructor

public class PatientController {

    private PatientService patientService;

    //Generate data by hitting this endpoint from POSTMAN
    @GetMapping("/generate")
    public Iterable<Patient> generateData() {
        return patientService.generateData();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable("id") Long id) {
        return patientService.getPatient(id);
    }

    @GetMapping("/getall")
    public List<Patient> getAll() {
        return patientService.getAllPatients();
    }

    @PostMapping("/add")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @PatchMapping("/update")
    public Patient updatePatient(@RequestBody Patient patient) {
        return patientService.updatePatient(patient);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

}
