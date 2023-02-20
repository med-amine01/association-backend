package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
}
