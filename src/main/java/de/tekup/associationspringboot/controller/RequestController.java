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

    @PostMapping("/add")
    public Request createRequest(@RequestBody Request request){
        return requestService.addRequest(request);
    }

    @PatchMapping("/update")
    public Request updateRequest(@RequestBody Request request){
        return requestService.updateRequest(request);
    }

    @DeleteMapping("/delete/{reqId}")
    public Request deleteRequest(@PathVariable("reqId") Long reqId){
        return requestService.removeRequest(reqId);
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
}
