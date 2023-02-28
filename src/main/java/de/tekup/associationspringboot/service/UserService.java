package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Role;
import de.tekup.associationspringboot.entity.User;
import de.tekup.associationspringboot.repository.RoleRepository;
import de.tekup.associationspringboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUser(String userEmail){
        return userRepository.findById(userEmail).orElseThrow(()-> new NoSuchElementException("No user found"));
    }

    public User updateUser(User user){
        if(userRepository.existsById(user.getUserEmail())){
            Set<Role> roleList = new HashSet<>();
            user.getRoles().forEach(r -> {
                roleList.add(roleRepository.findById(r.getRoleName()).orElse(null));
            });

            if(!roleList.isEmpty()){
                user.setRoles(roleList);
            }
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(String userEmail){
        if(!userRepository.existsById(userEmail)){
            throw new NoSuchElementException("No user found");
        }
        userRepository.deleteById(userEmail);
    }

    public User registerNewUser(User user){
        //User fetchedUser = userRepository.findById(user.getUserEmail()).orElse(null);
        if(!userRepository.existsById(user.getUserEmail())){
            Set<Role> roleList = new HashSet<>();
            user.getRoles().forEach(r -> {
                roleList.add(roleRepository.findById(r.getRoleName()).orElse(null));
            });

            if(!roleList.isEmpty()){
                user.setRoles(roleList);
            }
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));
            user.setActive(false);
            return userRepository.save(user);
        }
        return null;
    }


    public void initRolesAndUsers(){

        //---------- adding ADMIN_ROLE -----------------
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        adminRole.setRoleDescription("Admin Role");
        roleRepository.save(adminRole);

        //-------------- adding ROLE_FUNDER --------------
        Role funderRole = new Role();
        funderRole.setRoleName("ROLE_FUNDER");
        funderRole.setRoleDescription("Funder Role");
        roleRepository.save(funderRole);

        //-------------- adding ROLE_CEO --------------
        Role workerRole = new Role();
        workerRole.setRoleName("ROLE_WORKER");
        workerRole.setRoleDescription("Worker Role");
        roleRepository.save(workerRole);

        //-------------- adding ROLE_WORKER --------------
        Role ceoRole = new Role();
        ceoRole.setRoleName("ROLE_CEO");
        ceoRole.setRoleDescription("CEO Role");
        roleRepository.save(ceoRole);

        //-------------- adding ROLE_SG --------------
        Role SgRole = new Role();
        SgRole.setRoleName("ROLE_SG");
        SgRole.setRoleDescription("Secrétaire Generale Role");
        roleRepository.save(SgRole);



        //---------- adding ADMIN --------------
        User adminUser = new User();
        adminUser.setUserEmail("admin@test.com");
        adminUser.setUserFirstName("Admin");
        adminUser.setUserLastName("Admin");
        adminUser.setUserPassword(getEncodedPassword("admin123"));
        adminUser.setActive(true);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        //SETTING ADMIN ROLE
        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
