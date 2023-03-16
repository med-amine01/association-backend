package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.entity.RequestStatus;
import de.tekup.associationspringboot.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestService {

    private RequestRepository requestRepository;

    public Request addRequest(Request request){
        request.setRequestStatus(RequestStatus.REVIEW);
        return requestRepository.save(request);
    }

    public Request updateRequest(){
        return null;
    }

    public void dividingMoney(){
    }
}
