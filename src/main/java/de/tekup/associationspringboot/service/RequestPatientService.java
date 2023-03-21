package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Patient;
import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.entity.RequestPatient;
import de.tekup.associationspringboot.entity.RequestPatientId;
import de.tekup.associationspringboot.repository.RequestPatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestPatientService {
    private final RequestPatientRepository requestPatientRepository;

    public void addRequestToPatient(Request request, Patient patient, double amount){
        requestPatientRepository.save(new RequestPatient(
                new RequestPatientId(request.getId(),patient.getId()),
                patient,
                request,
                amount
        ));
    }
}
