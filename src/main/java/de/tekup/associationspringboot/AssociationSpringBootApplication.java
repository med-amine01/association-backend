package de.tekup.associationspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AssociationSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssociationSpringBootApplication.class, args);
    }

}
