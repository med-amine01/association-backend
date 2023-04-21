package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.entity.RequestProject;
import de.tekup.associationspringboot.entity.RequestProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public interface RequestProjectRepository extends JpaRepository<RequestProject, RequestProjectId> {

    //we will return all data related to request
    //List<RequestProject> findAllByRequest(Request requestId);
}
