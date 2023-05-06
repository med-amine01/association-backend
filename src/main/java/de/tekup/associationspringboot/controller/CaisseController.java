package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.service.CaisseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/caisse")
@AllArgsConstructor
public class CaisseController {
    private CaisseService caisseService;
    @GetMapping("/getsolde")
    public Double getAmount(){
        return caisseService.getCaisse().getSolde();
    }
    @PostMapping("/Addsolde")
    public Double AjouterSolde(@RequestBody Double amount){
         caisseService.Ajouter(amount);
         return caisseService.getCaisse().getSolde();
    }
    @PostMapping ("/retiresolde")
    public Double RetireSolde(@RequestBody Double amount){
        caisseService.retirer(amount);
        return caisseService.getCaisse().getSolde();
    }
}
