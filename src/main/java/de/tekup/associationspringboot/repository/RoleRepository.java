package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("http://localhost:4200")
public interface RoleRepository extends CrudRepository<Role, String> {
}
