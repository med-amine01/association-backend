package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.User;
import de.tekup.associationspringboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PatchMapping("/update/{oldEmail}")
    public User updatePatient(@RequestBody User user , @PathVariable String oldEmail){
        if(userService.updateUser(user,oldEmail) == null){
            //request value error 302
            // the new userEmail found in DB so we shouldn't update email
            throw new ResponseStatusException(HttpStatus.FOUND, "User Email already exists");
        }
        return userService.updateUser(user, oldEmail);
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
