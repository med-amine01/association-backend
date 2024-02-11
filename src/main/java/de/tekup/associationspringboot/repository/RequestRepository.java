package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.Request;
import de.tekup.associationspringboot.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findRequestByRequestStatus(RequestStatus status);

    List<Request> findRequestByRequestToFunderStatus(RequestStatus status);

    List<Request> findAllByFunder_Uuid(String uuid);
}
