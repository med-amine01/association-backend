package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.Role;
import de.tekup.associationspringboot.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @PostMapping("/createNewRole")
    public Role createNewRole(@RequestBody Role role){
        return  roleService.createNewRole(role);
    }

    @GetMapping("/getall")
    public List<Role> getRoles(){
        return roleService.getAllRoles();
    }
}
