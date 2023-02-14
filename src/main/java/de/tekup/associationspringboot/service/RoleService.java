package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Role;
import de.tekup.associationspringboot.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Role createNewRole(Role role){
        return roleRepository.save(role);
    }
}
