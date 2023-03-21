package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Patient;
import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.entity.RequestStatus;
import de.tekup.associationspringboot.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RequestService {
    private RequestRepository requestRepository;
    private RequestPatientService requestPatientService;
    private PatientService patientService;

    public Request addRequest(Request request){
        request.setRequestStatus(RequestStatus.REVIEW);
        return requestRepository.save(request);
    }

    public List<Request> getRequestByStatus(String status){
        if(status.equals("REVIEW")){
            return requestRepository.findRequestByRequestStatus(RequestStatus.REVIEW);
        }

        if(status.equals("ACCEPTED_ADMIN")){
            return requestRepository.findRequestByRequestStatus(RequestStatus.ACCEPTED_ADMIN);
        }
        if(status.equals("REFUSED_ADMIN")){
            return requestRepository.findRequestByRequestStatus(RequestStatus.REFUSED_ADMIN);
        }

        if(status.equals("ACCEPTED_CEO")){
            return requestRepository.findRequestByRequestStatus(RequestStatus.ACCEPTED_CEO);
        }
        if(status.equals("REFUSED_CEO")){
            return requestRepository.findRequestByRequestStatus(RequestStatus.REFUSED_CEO);
        }

        if(status.equals("ACCEPTED_SG")){
            return requestRepository.findRequestByRequestStatus(RequestStatus.ACCEPTED_SG);
        }
        if(status.equals("REFUSED_SG")){
            return requestRepository.findRequestByRequestStatus(RequestStatus.REFUSED_SG);
        }

        return null;
    }

    public List<Request> getAll(){
        return requestRepository.findAll();
    }
    public Request getRequest(Long id){
        return requestRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No Request with ID : " + id));
    }

    public Request updateRequest(){
        return null;
    }

    public void splitAmountOnSelectedPatients(Request request){
        if(request.getRequestedAmount() > 0){
            double eachAmount = request.getRequestedAmount() / request.getPatients().size();

            request.getPatients().forEach(patient -> {
                requestPatientService.addRequestToPatient(request,patientService.getPatient(patient.getId()),eachAmount);
                //substract that splitted amount from the amount patient funding needed
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
}
