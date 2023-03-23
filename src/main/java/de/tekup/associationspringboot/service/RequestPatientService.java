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
    private RequestService requestService;

    public void addRequestToPatient(Request request, Patient patient, double amount){
        requestPatientRepository.save(new RequestPatient(
                new RequestPatientId(request.getId(),patient.getId()),
                patient,
                request,
                amount
        ));
    }


    public void splitAmountOnSelectedPatients(Request request){
        if(request.getRequestedAmount() > 0){
            double eachAmount = request.getRequestedAmount() / request.getPatients().size();

            request.getPatients().forEach(patient -> {
                addRequestToPatient(request,patientService.getPatient(patient.getId()),eachAmount);
                //substract that splitted amount from the amount patient funding needed
                //TODO : tanseech tarja3 w tverifi keen demande accepté w ela lee bech taaml soustraction
                updateAmount(patient.getId(), eachAmount);
            });
        }
    }

    private void updateAmount(Long patientId, double amount){
        Patient p = patientService.getPatient(patientId);
        String val = String.format("%.2f",p.getFundingNeeded() - amount);
        System.err.println(val);
        p.setFundingNeeded(Double.parseDouble(val));
        patientService.updatePatient(p);
    }
    public List<RequestPatient> getRpByRequest(Long id){
        Request request = requestService.getRequest(id);
        return new ArrayList<>(requestPatientRepository.findAllByRequest(request));
    }
}
