package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Patient;
import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.entity.RequestPatient;
import de.tekup.associationspringboot.entity.RequestPatientId;
import de.tekup.associationspringboot.repository.RequestPatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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


            request.getPatients().forEach(patient -> {
                //patientService.getPatient(patient.getId())
                addRequestToPatient(request,patient,Math.round(eachAmount));
                //substract that splitted amount from the amount patient funding needed
                //patient.getId()
                updateAmount(patient.getId(), Math.round(eachAmount));
            });
        }
    }

    private void updateAmount(Long patientId, double amount){
        Patient p = patientService.getPatient(patientId);
        //String val = String.format("%.2f",p.getFundingNeeded() - amount);
        double val = Math.round(p.getFundingNeeded() - amount);
        if(val <= 0){
            p.setFundingNeeded(0);
        }
        else{
            p.setFundingNeeded(val);
        }
        patientService.updatePatient(p);
    }
}
