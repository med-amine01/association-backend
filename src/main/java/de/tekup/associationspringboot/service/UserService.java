package de.tekup.associationspringboot.service;

import com.github.javafaker.Faker;
import de.tekup.associationspringboot.entity.Account;
import de.tekup.associationspringboot.entity.Role;
import de.tekup.associationspringboot.entity.User;
import de.tekup.associationspringboot.repository.AccountRepository;
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
    private AccountRepository accountRepository;


    public List<User> getUsersByCriteria(String role, boolean active) {
        return new ArrayList<>(userRepository.findAllByRolesRoleNameAndActive(role, active));
    }

    public List<User> getAll() {
        return new ArrayList<>(userRepository.findAll());
    }

    public User getUser(String userUid) {
        return userRepository.findUserByUuid(userUid);
    }


    public User updateUser(User user, String uuid) {

        User current = userRepository.findUserByUuid(uuid);
        if (user.getUserFirstName() != null) {
            current.setUserFirstName(user.getUserFirstName());
        }
        if (user.getUserLastName() != null) {
            current.setUserLastName(user.getUserLastName());
        }
        if (user.getAddress() != null) {
            current.setAddress(user.getAddress());
        }
        if (user.getPhone() != null) {
            current.setPhone(user.getPhone());
        }
        if (!user.getRoles().isEmpty()) {
            Set<Role> roleList = new HashSet<>();
            user.getRoles().forEach(r -> roleList.add(roleRepository.findById(r.getRoleName()).orElse(null)));

            if (!roleList.isEmpty()) {
                current.setRoles(roleList);
            }
        }

        return userRepository.save(current);
    }

    public User getUserByAccountId(Long accountId) {
        return userRepository.findByAccountId(accountId);
    }


    //DEPRECATED
    /*public void deleteUser(String userEmail) {
        if(!userRepository.existsById(userEmail)){
            throw new NoSuchElementException("No user found");
        }
        //NOTE : Always set the sub childs to null to remove relationships in database then delete the object
        User user = userRepository.findById(userEmail).get();
        user.setRoles(null);
        //user.setAccount(null);
        user.setAccount(null);
        userRepository.delete(user);
    }*/

    public User disableUser(String uuid) {
        User user = userRepository.findUserByUuid(uuid);
        if (user == null) {
            throw new NoSuchElementException("No such user found");
        }

        user.getAccount().forEach(account -> {
            account.setEnable(false);
        });

        user.setActive(false);
        return userRepository.save(user);
    }

    public User enableUser(String uuid) {
        User user = userRepository.findUserByUuid(uuid);
        if (user == null) {
            throw new NoSuchElementException("No such user found");
        }

        user.setActive(true);
        user.getAccount().forEach(account -> {
            account.setEnable(true);
        });

        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new NoSuchElementException("No User with Email : " + email));
    }

    public User registerNewUser(User user) {
        //User fetchedUser = userRepository.findById(user.getUserEmail()).orElse(null);
        if (!userRepository.existsById(user.getUserEmail())) {
            Set<Role> roleList = new HashSet<>();
            user.getRoles().forEach(r -> {
                roleList.add(roleRepository.findById(r.getRoleName()).orElse(null));

                //setting account
                if (r.getRoleName().equals("ROLE_FUNDER")) {
                    Account acc = new Account();
                    acc.setCurrentBalance(0);
                    acc.setTotalBalance(0);
                    acc.setFunder(user);
                    acc.setTransactionHistories(null);
                    acc.setEnable(true);
                    accountRepository.save(acc);
                    if (user.getAccount() != null) {
                        user.getAccount().add(acc);
                    } else {
                        List<Account> tempListAccount = new ArrayList<>();
                        tempListAccount.add(acc);
                        user.setAccount(tempListAccount);
                    }
                }
            });

            if (!roleList.isEmpty()) {
                user.setRoles(roleList);
            }
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));
            //setting uuid to the user
            user.setUuid(UUID.randomUUID().toString());
            user.setActive(true);


            return userRepository.save(user);
        }
        return null;
    }


    public void initRolesAndUsers() {

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
        Role sgRole = new Role();
        sgRole.setRoleName("ROLE_SG");
        sgRole.setRoleDescription("Secrétaire Generale Role");
        roleRepository.save(sgRole);


        //---------- adding ADMIN --------------
        User adminUser = new User();
        adminUser.setUserEmail("admin@test.com");
        adminUser.setUserFirstName("Admin");
        adminUser.setUserLastName("admin");
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

        //---------- adding worker --------------
        User worker = new User();
        worker.setUserEmail("worker@test.com");
        worker.setUserFirstName("Worker");
        worker.setUserLastName("worker");
        worker.setUserPassword(getEncodedPassword("worker123"));
        worker.setPhone("12345678");
        worker.setAddress("1235 xyz 456 ");
        worker.setActive(true);
        Set<Role> workerRoles = new HashSet<>();
        workerRoles.add(workerRole);
        //SETTING ADMIN ROLE
        worker.setUuid(UUID.randomUUID().toString());
        worker.setRoles(workerRoles);
        userRepository.save(worker);

        //---------- adding sg --------------
        User sg = new User();
        sg.setUserEmail("sg@test.com");
        sg.setUserFirstName("sg");
        sg.setUserLastName("sg");
        sg.setUserPassword(getEncodedPassword("sg123"));
        sg.setPhone("12345678");
        sg.setAddress("1235 xyz 456 ");
        sg.setActive(true);
        Set<Role> sgRoles = new HashSet<>();
        sgRoles.add(sgRole);
        //SETTING ADMIN ROLE
        sg.setUuid(UUID.randomUUID().toString());
        sg.setRoles(sgRoles);
        userRepository.save(sg);

        //---------- adding sg --------------
        User ceo = new User();
        ceo.setUserEmail("ceo@test.com");
        ceo.setUserFirstName("ceo");
        ceo.setUserLastName("ceo");
        ceo.setUserPassword(getEncodedPassword("ceo123"));
        ceo.setPhone("12345678");
        ceo.setAddress("1235 xyz 456 ");
        ceo.setActive(true);
        Set<Role> ceoRoles = new HashSet<>();
        ceoRoles.add(ceoRole);
        //SETTING ADMIN ROLE
        ceo.setUuid(UUID.randomUUID().toString());
        ceo.setRoles(ceoRoles);
        userRepository.save(ceo);

        //---------- adding FUNDER --------------
        User funderUser = new User();
        funderUser.setUserEmail("funder@test.com");
        funderUser.setUserFirstName("Funder");
        funderUser.setUserLastName("Funder");
        funderUser.setUserPassword(getEncodedPassword("funder123"));
        funderUser.setPhone("55123456");
        funderUser.setAddress("1235 xyz 456 ");
        funderUser.setActive(true);
        Set<Role> funderRoles = new HashSet<>();
        funderRoles.add(funderRole);
        //SETTING funder ROLE
        funderUser.setUuid(UUID.randomUUID().toString());
        funderUser.setRoles(funderRoles);
        userRepository.save(funderUser);

        Account acc = new Account();
        acc.setCurrentBalance(0);
        acc.setTotalBalance(0);
        acc.setTransactionHistories(null);
        acc.setFunder(funderUser);
        acc.setEnable(true);
        accountRepository.save(acc);
    }


    //GENERATING FAKE DATA FOR FUNDER
    public List<User> generateFunders() {
        Faker faker = new Faker();
        for (int i = 0; i < 3; i++) {
            User u = new User();
            String name = faker.name().firstName();
            u.setUserEmail(name.toLowerCase() + "@test.com");
            u.setUserFirstName(name);
            u.setUserLastName(faker.name().lastName());
            u.setPhone(faker.phoneNumber().cellPhone());
            u.setAddress(faker.address().fullAddress());
            u.setUuid(UUID.randomUUID().toString());
            u.setActive(true);
            u.setUserPassword(getEncodedPassword("funder123"));
            Set<Role> role = new HashSet<>();
            role.add(roleRepository.findById("ROLE_FUNDER").get());
            u.setRoles(role);
            userRepository.save(u);

            Account acc = new Account();
            acc.setCurrentBalance(0);
            acc.setTotalBalance(0);
            acc.setTransactionHistories(null);
            acc.setFunder(u);
            acc.setEnable(true);
            accountRepository.save(acc);
        }

        generateUsers();

        return userRepository.findAllByRolesRoleName("ROLE_FUNDER");
    }

    private void generateUsers() {
        Faker faker = new Faker();

        //generate worker
        for (int i = 0; i < 2; i++) {
            User u = new User();
            String name = faker.name().firstName();
            u.setUserEmail(name.toLowerCase() + "@test.com");
            u.setUserFirstName(name);
            u.setUserLastName(faker.name().lastName());
            u.setPhone(faker.phoneNumber().cellPhone());
            u.setAddress(faker.address().fullAddress());
            u.setUuid(UUID.randomUUID().toString());
            u.setActive(true);
            u.setUserPassword(getEncodedPassword("worker123"));
            Set<Role> role = new HashSet<>();
            role.add(roleRepository.findById("ROLE_WORKER").get());
            u.setRoles(role);
            u.setAccount(null);
            userRepository.save(u);
        }

        //generate SG
        User u = new User();
        String name = faker.name().firstName();
        u.setUserEmail(name.toLowerCase() + "@test.com");
        u.setUserFirstName(name);
        u.setUserLastName(faker.name().lastName());
        u.setPhone(faker.phoneNumber().cellPhone());
        u.setAddress(faker.address().fullAddress());
        u.setUuid(UUID.randomUUID().toString());
        u.setActive(true);
        u.setUserPassword(getEncodedPassword("sg123"));
        Set<Role> role = new HashSet<>();
        role.add(roleRepository.findById("ROLE_SG").get());
        u.setRoles(role);
        u.setAccount(null);
        userRepository.save(u);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
