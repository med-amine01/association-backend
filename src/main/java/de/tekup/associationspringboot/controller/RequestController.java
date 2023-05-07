package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.enums.RequestStatus;
import de.tekup.associationspringboot.service.CaisseService;
import de.tekup.associationspringboot.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/request")
@AllArgsConstructor

public class RequestController {

    private RequestService requestService;
    private CaisseService caisseService;

    @PostMapping("/add")
    public Request createRequest(@RequestBody Request request){
        return requestService.addRequest(request);
    }

    @PatchMapping("/update")
    public Request updateRequest(@RequestBody Request request){
        if (request.getRequestStatus()== RequestStatus.ACCEPTED_ADMIN||request.getRequestStatus()==RequestStatus.ACCEPTED_SG){
            caisseService.retirer(request.getRequestedAmount());

        }
        return requestService.updateRequest(request);
    }

    @DeleteMapping("/delete/{reqId}")
    public ResponseEntity<?> deleteRequest(@PathVariable("reqId") Long reqId){
        try {
            requestService.removeRequest(reqId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            //this exception will appear if you try to delete accepted requests
            System.err.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR DELETING");
        }
    }

    @GetMapping("/getAll")
    public List<Request> getAllRequests(){
        return requestService.getAll();
    }

    @GetMapping("/get/{reqId}")
    public Request getRequest(@PathVariable("reqId") Long reqId){
        return requestService.getRequest(reqId);
    }

    @GetMapping("/requestStatus/{status}")
    public List<Request> reviewRequests(@PathVariable("status") String status){
        return requestService.getRequestByStatus(status);
    }

    @GetMapping("/funder/{uid}")
    public List<Request> getByUid(@PathVariable("uid")String uid) {
        return requestService.getRequestsByFunderUid(uid);
    }
}
