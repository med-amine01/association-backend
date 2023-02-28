package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Role;
import de.tekup.associationspringboot.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Role createNewRole(Role role){
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles(){
        List<Role> roleList = new ArrayList<>();
        roleRepository.findAll().forEach(roleList::add);
        return roleList;
    }
}
