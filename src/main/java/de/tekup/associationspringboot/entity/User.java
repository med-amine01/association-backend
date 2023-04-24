package de.tekup.associationspringboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    private String userEmail;

    @Column(unique = true)
    private String uuid;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String phone;
    private String address;
    private boolean active;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Request> requests;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    //this will create third table called USER_ROLE with two columns =>  USER_ID ; ROLE_ID
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    private Set<Role> roles;

    @OneToMany
    private List<Account> account;

}
