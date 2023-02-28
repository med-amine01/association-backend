package de.tekup.associationspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String phone;
    private String address;
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    //this will create third table called USER_ROLE with two columns =>  USER_ID ; ROLE_ID
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    private Set<Role> roles;
}
