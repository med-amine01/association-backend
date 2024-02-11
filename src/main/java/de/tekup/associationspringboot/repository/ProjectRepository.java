package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("http://localhost:4200")
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
