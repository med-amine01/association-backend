package de.tekup.associationspringboot.repository;

import de.tekup.associationspringboot.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public interface AccountRepository extends JpaRepository<Account,Long> {

    //void deleteAccountsByFunder_UserEmail(String userEmail);

    List<Account> findByFunder_Uuid(String uuid);

}
