package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Caisse;
import de.tekup.associationspringboot.repository.CaisseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CaisseService {
    private CaisseRepository caisseRepository;
    public Caisse getCaisse (){
        if(!caisseRepository.existsById(1L)) {
            return caisseRepository.save(Caisse.getInstance());
        }
        return caisseRepository.findById(1L).get();
    }

    public Caisse retirer(double amount){
        Caisse c=caisseRepository.findById(1L)
                .orElseThrow(()-> new NoSuchElementException("Caisse Not Found " ));
        c.retirer(amount);
        return caisseRepository.save(c);
    }
    public Caisse Ajouter(double amount){
        Caisse c=caisseRepository.findById(1L)
                .orElseThrow(()-> new NoSuchElementException("Caisse Not Found " ));
        c.ajouter(amount);
        return caisseRepository.save(c);
    }
}
