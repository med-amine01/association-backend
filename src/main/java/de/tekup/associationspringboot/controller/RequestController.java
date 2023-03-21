package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
@AllArgsConstructor
public class RequestController {

    private RequestService requestService;

    @GetMapping("/split/{idReq}")
    public void splitted(@PathVariable("idReq") Long idReq){
        Request request = requestService.getRequest(idReq);
        requestService.splitAmountOnSelectedPatients(request);
    }

    @GetMapping("/getAll")
    public List<Request> getAllRequests(){
        return requestService.getAll();
    }

    @GetMapping("/requestStatus/{status}")
    public List<Request> reviewRequests(@PathVariable("status") String status){
        return requestService.getRequestByStatus(status);
    }
    @PostMapping("/add")
    public void createRequest(@RequestBody Request request){
        Request r =  requestService.addRequest(request);
        requestService.splitAmountOnSelectedPatients(r);
    }
}
