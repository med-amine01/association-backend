package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
}
