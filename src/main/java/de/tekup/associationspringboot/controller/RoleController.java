package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Role;
import de.tekup.associationspringboot.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @PostMapping("/createNewRole")
    public Role createNewRole(@RequestBody Role role){
        return  roleService.createNewRole(role);
    }
}
