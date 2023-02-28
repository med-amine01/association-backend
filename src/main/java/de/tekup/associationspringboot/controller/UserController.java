package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.User;
import de.tekup.associationspringboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    @PostMapping("/addUser")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public User addUser(@RequestBody User user){
        return  userService.registerNewUser(user);
    }

    @GetMapping("/{userEmail}")
    public User getUser(@PathVariable("userEmail") String userEmail){
        return userService.getUser(userEmail);
    }
    @GetMapping("/getall")
    public List<User> getAll(){
        return userService.getAll();
    }

    @PatchMapping("/update")
    public User updatePatient(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{userEmail}")
    public void deletePatient(@PathVariable String userEmail){
        userService.deleteUser(userEmail);
    }


    //this methode will be executed after the application is built
    @PostConstruct
    public void iniRolesAndUsers(){
        userService.initRolesAndUsers();
    }

}
