package de.tekup.associationspringboot.service;

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

    public Request addRequest(Request request){
        request.setRequestStatus(RequestStatus.REVIEW);
        return requestRepository.save(request);
    }
    public Request updateRequest(Request request){

        if(!requestRepository.existsById(request.getId())){
            throw new NoSuchElementException("No Request With ID: " + request.getId());
        }

        String status = request.getRequestStatus().toString();
        if(status.startsWith("ACCEPTED")){
            requestPatientService.splitAmountOnSelectedPatients(request);

            if(status.equals("ACCEPTED_ADMIN"))
                request.setRequestStatus(RequestStatus.ACCEPTED_ADMIN);
            if(status.equals("ACCEPTED_CEO"))
                request.setRequestStatus(RequestStatus.ACCEPTED_CEO);
            if(status.equals("ACCEPTED_SG"))
                request.setRequestStatus(RequestStatus.ACCEPTED_SG);
        }
        else{
            if(status.equals("REFUSED_ADMIN"))
                request.setRequestStatus(RequestStatus.REFUSED_ADMIN);

            if(status.equals("REFUSED_CEO"))
                request.setRequestStatus(RequestStatus.REFUSED_CEO);

            if(status.equals("REFUSED_SG"))
                request.setRequestStatus(RequestStatus.REFUSED_SG);
        }
        return requestRepository.save(request);
    }
    public Request removeRequest(Long reqId){
        Request request = getRequest(reqId);
        requestRepository.delete(request);
        return request;
    }
    public Request getRequest(Long id){
        return requestRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No Request with ID : " + id));
    }
    public List<Request> getAll(){
        return requestRepository.findAll();
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


}
