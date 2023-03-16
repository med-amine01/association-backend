package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/request")
@AllArgsConstructor
public class RequestController {

    private RequestService requestService;

    @PostMapping("/add")
    public Request createPatient(@RequestBody Request request){
        return requestService.addRequest(request);
    }
}
