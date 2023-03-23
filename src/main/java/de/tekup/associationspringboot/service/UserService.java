package de.tekup.associationspringboot.service;

import de.tekup.associationspringboot.entity.Role;
import de.tekup.associationspringboot.entity.User;
import de.tekup.associationspringboot.repository.RoleRepository;
import de.tekup.associationspringboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public User getUser(String userUid){
        User u = userRepository.findUserByUuid(userUid);
        if(u == null){
            return null;
        }
        return userRepository.findUserByUuid(userUid);
    }

//    @Transactional
//    public User updateUser(User user, String oldEmail){
//        User user1 = userRepository.findById(oldEmail).orElseThrow(()-> new NoSuchElementException("No user found"));
//
//        if(user.getUserEmail() == null){
//
//        }
//        //if the new Email exists in data base
//        if(!userRepository.existsById(user.getUserEmail())) {
//
//            if (!user.getRoles().isEmpty()) {
//                try {
//                    Set<Role> roleList = new HashSet<>();
//                    user.getRoles().forEach(r -> {
//                        roleList.add(roleRepository.findById(r.getRoleName()).orElse(null));
//                    });
//                }
//                catch (Exception e){
//                    System.out.println(e.getMessage());
//                }
//
//            } else {
//                //setting the same roles
//                user.setRoles(user1.getRoles());
//            }
//
//            if (user.getUserPassword() == null) {
//                user.setUserPassword(getEncodedPassword(user1.getUserPassword()));
//            } else {
//                user.setUserPassword(getEncodedPassword(user.getUserPassword()));
//            }
//
//            //checking if the user updates his email
//            if (user.getUserEmail() != null) {
//                userRepository.updateUserEmail(user.getUserEmail(), oldEmail);
//            }
//            return userRepository.save(user);
//        }
//        return null;
//    }

    public User updateUser(User user, String uuid) {

        User current = userRepository.findUserByUuid(uuid);
        if(user.getUserFirstName() != null){
            current.setUserFirstName(user.getUserFirstName());
        }
        if(user.getUserLastName() != null){
            current.setUserLastName(user.getUserLastName());
        }
        if(user.getAddress() != null){
            current.setAddress(user.getAddress());
        }
        if(user.getPhone() != null){
            current.setPhone(user.getPhone());
        }
        if(!user.getRoles().isEmpty()){
            Set<Role> roleList = new HashSet<>();
            user.getRoles().forEach(r -> {
                roleList.add(roleRepository.findById(r.getRoleName()).orElse(null));
            });

            if(!roleList.isEmpty()){
                current.setRoles(roleList);
            }
        }

        return userRepository.save(current);
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
            //setting uuid to the user
            user.setUuid(UUID.randomUUID().toString());
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
        adminUser.setPhone("55123456");
        adminUser.setAddress("1235 xyz 456 ");
        adminUser.setActive(true);
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        //SETTING ADMIN ROLE
        adminUser.setUuid(UUID.randomUUID().toString());
        adminUser.setRoles(adminRoles);
        userRepository.save(adminUser);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
