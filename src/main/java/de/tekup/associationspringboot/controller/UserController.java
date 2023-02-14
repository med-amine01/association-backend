package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Role;
import de.tekup.associationspringboot.entity.User;
import de.tekup.associationspringboot.service.RoleService;
import de.tekup.associationspringboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    @PostMapping("/registerNewUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User createNewRole(@RequestBody User user){
        return  userService.registerNewUser(user);
    }


    //this methode will be executed after the application is built
    @PostConstruct
    public void iniRolesAndUsers(){
        userService.initRolesAndUsers();
    }

}
