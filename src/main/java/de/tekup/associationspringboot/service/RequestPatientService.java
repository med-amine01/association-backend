package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Patient;
import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.entity.RequestPatient;
import de.tekup.associationspringboot.entity.RequestPatientId;
import de.tekup.associationspringboot.repository.RequestPatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestPatientService {
    private final RequestPatientRepository requestPatientRepository;
    private PatientService patientService;

    public void addRequestToPatient(Request request, Patient patient, double amount){
        requestPatientRepository.save(new RequestPatient(
                new RequestPatientId(request.getId(),patient.getId()),
                patient,
                request,
                amount
        ));
    }

    public List<RequestPatient> getAllByRequest(Request request){
        return requestPatientRepository.findAllByRequest(request);
    }


    public void splitAmountOnSelectedPatients(Request request){
        if(request.getRequestedAmount() > 0){
            double eachAmount = request.getRequestedAmount() / request.getPatients().size();
            String val = String.format("%.2f",eachAmount);
            request.getPatients().forEach(patient -> {
                //patientService.getPatient(patient.getId())
                addRequestToPatient(request,patient,Double.parseDouble(val));
                //substract that splitted amount from the amount patient funding needed
                //patient.getId()
                updateAmount(patient.getId(), Double.parseDouble(val));
            });
        }
    }

    private void updateAmount(Long patientId, double amount){
        //TODO : thabet fi funding needed 9bal ma taaml substrction
        Patient p = patientService.getPatient(patientId);
        String val = String.format("%.2f",p.getFundingNeeded() - amount);
        p.setFundingNeeded(Double.parseDouble(val));
        patientService.updatePatient(p);
    }
}
