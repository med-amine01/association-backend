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
    private RequestProjectService requestProjectService;

    public Request addRequest(Request request) {
        if (request.getProjects() != null) {
            request.setRequestToFunderStatus(RequestStatus.REQUEST_PENDING);
        }
        request.setRequestStatus(RequestStatus.REVIEW);

        return requestRepository.save(request);
    }
    public Request updateRequest(Request request) {
        if(!requestRepository.existsById(request.getId())){
            throw new NoSuchElementException("No Request With ID: " + request.getId());
        }

        if(request.getRequestStatus() != null) {
            String status = request.getRequestStatus().toString();

            if(status.equals("ACCEPTED_TO_SG")) {
                request.setRequestStatus(RequestStatus.ACCEPTED_TO_SG);
            }
            if(status.equals("ACCEPTED_TO_CEO")) {
                request.setRequestStatus(RequestStatus.ACCEPTED_TO_CEO);
            }

            if(status.startsWith("ACCEPTED") && !status.contains("ACCEPTED_TO_")) {

                requestProjectService.splitAmountOnSelectedProjects(request);

                //TODO : lina tnajem to3erdhna mochkela puisque user ynajem ykoun 3andou akther men role
                //TODO : arje3 w badel logique bech ma yssirech confusion fi role
                if(status.equals("ACCEPTED_ADMIN"))
                    request.setRequestStatus(RequestStatus.ACCEPTED_ADMIN);
                if(status.equals("ACCEPTED_SG"))
                    request.setRequestStatus(RequestStatus.ACCEPTED_SG);

                request.setRequestToFunderStatus(RequestStatus.REQUEST_ACCEPTED);
            }
            else {
                if(status.equals("REFUSED_ADMIN"))
                    request.setRequestStatus(RequestStatus.REFUSED_ADMIN);

                if(status.equals("REFUSED_CEO"))
                    request.setRequestStatus(RequestStatus.REFUSED_CEO);

                if(status.equals("REFUSED_SG"))
                    request.setRequestStatus(RequestStatus.REFUSED_SG);

                if(status.contains("ACCEPTED_TO")) {
                    request.setRequestToFunderStatus(request.getRequestToFunderStatus());
                }
                else {
                    request.setRequestToFunderStatus(RequestStatus.REQUEST_REFUSED);
                }
            }
        }

        return requestRepository.save(request);
    }

    public void removeRequest(Long reqId){
        Request request = getRequest(reqId);
        requestRepository.delete(request);
    }
    public Request getRequest(Long id){
        return requestRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("No Request with ID : " + id));
    }
    public List<Request> getAll(){
        return requestRepository.findAll();
    }
    public List<Request> getRequestByStatus(String status) {
        if(status.equals("PENDING") || status.equals("REQUEST_ACCEPTED") || status.equals("REQUEST_REFUSED")) {
            return requestRepository.findRequestByRequestToFunderStatus(convertStringStatusToObject(status));
        }
       return requestRepository.findRequestByRequestStatus(convertStringStatusToObject(status));
    }

    private RequestStatus convertStringStatusToObject(String status) {

        //WORKER
        if(status.equals("REVIEW")){
            return RequestStatus.REVIEW;
        }


        //ADMIN
        if(status.equals("ACCEPTED_ADMIN")){
            return RequestStatus.ACCEPTED_ADMIN;
        }
        if(status.equals("ACCEPTED_TO_CEO")){
            return RequestStatus.ACCEPTED_TO_CEO;
        }
        if(status.equals("REFUSED_ADMIN")){
            return RequestStatus.REFUSED_ADMIN;
        }


        //CEO
        if(status.equals("ACCEPTED_TO_SG")){
            return RequestStatus.ACCEPTED_TO_SG;
        }
        if(status.equals("REFUSED_CEO")){
            return RequestStatus.REFUSED_CEO;
        }


        //SG
        if(status.equals("ACCEPTED_SG")){
            return RequestStatus.ACCEPTED_SG;
        }
        if(status.equals("REFUSED_SG")){
            return RequestStatus.REFUSED_SG;
        }


        //funder
        if(status.equals("REQUEST_PENDING")){
            return RequestStatus.REQUEST_PENDING;
        }
        if(status.equals("REQUEST_CANCELED")){
            return RequestStatus.REQUEST_CANCELED;
        }
        if(status.equals("REQUEST_ACCEPTED")){
            return RequestStatus.REQUEST_ACCEPTED;
        }
        if(status.equals("REQUEST_REFUSED")){
            return RequestStatus.REQUEST_REFUSED;
        }
        return null;
    }


}
