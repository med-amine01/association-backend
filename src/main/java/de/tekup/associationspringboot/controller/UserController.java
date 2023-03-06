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
        if(userService.registerNewUser(user) == null){
            throw new ResponseStatusException(HttpStatus.FOUND, "User Already Exists");
        }
        return  userService.registerNewUser(user);
    }

    @GetMapping("/{uuid}")
    public User getUser(@PathVariable("uuid") String userUid){
        if(userService.getUser(userUid) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
        }
        return userService.getUser(userUid);
    }
    @GetMapping("/getall")
    public List<User> getAll(){
        return userService.getAll();
    }

    @PatchMapping("/update/{uuid}")
    public User updatePatient(@RequestBody User user, @PathVariable String uuid){
        if(userService.updateUser(user, uuid) == null){
            throw new ResponseStatusException(HttpStatus.FOUND, "User Email already exists");
        }
        return userService.updateUser(user,uuid);
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
