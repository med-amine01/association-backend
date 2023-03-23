package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Patient;
import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.entity.RequestPatient;
import de.tekup.associationspringboot.service.RequestPatientService;
import de.tekup.associationspringboot.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/request")
@AllArgsConstructor
public class RequestController {

    private RequestService requestService;
    private RequestPatientService requestPatientService;


    @GetMapping("/rp/{idReq}")
    public List<Patient> getPatientsByRequest(@PathVariable("idReq") Long idReq){
        List<Patient> patients = new ArrayList<>();
        requestPatientService.getRpByRequest(idReq).forEach(requestPatient -> {
            patients.add(requestPatient.getPatient());
        });
        return patients;
    }

    @GetMapping("/split/{idReq}")
    public void splitted(@PathVariable("idReq") Long idReq){
        Request request = requestService.getRequest(idReq);
        requestPatientService.splitAmountOnSelectedPatients(request);
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
        requestPatientService.splitAmountOnSelectedPatients(r);
    }
}
