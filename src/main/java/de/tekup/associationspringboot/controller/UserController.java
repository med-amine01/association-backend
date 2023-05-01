package de.tekup.associationspringboot.controller;

import de.tekup.associationspringboot.entity.User;
import de.tekup.associationspringboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;


    @GetMapping("/getBy/{role}/{active}")
    public List<User> getByCriteria(@PathVariable("role") String role , @PathVariable("active") String active) {
        if(active.equals("1")) {
            return userService.getUsersByCriteria(role,true);
        }
        return userService.getUsersByCriteria(role,false);
    }

    @PostMapping("/addUser")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public User addUser(@RequestBody User user) {
        if(userService.registerNewUser(user) == null) {
            throw new ResponseStatusException(HttpStatus.FOUND, "User Already Exists");
        }
        return  userService.registerNewUser(user);
    }

    @GetMapping("/{uuid}")
    public User getUser(@PathVariable("uuid") String userUid) {
        if(userService.getUser(userUid) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
        }
        return userService.getUser(userUid);
    }
    @GetMapping("/getall")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PatchMapping("/update/{uuid}")
    public User updateUser(@RequestBody User user, @PathVariable String uuid){
        if(userService.updateUser(user, uuid) == null) {
            throw new ResponseStatusException(HttpStatus.FOUND, "User Email already exists");
        }
        return userService.updateUser(user,uuid);
    }

    @DeleteMapping("/disable/{uuid}")
    public User disableUser(@PathVariable String uuid) {
        return userService.disableUser(uuid);
    }

    @PatchMapping("/enable/{uuid}")
    public User enableUser(@PathVariable String uuid) {
        return userService.enableUser(uuid);
    }

    //this methode will be executed after the application is built
    @PostConstruct
    public void iniRolesAndUsers(){
        userService.initRolesAndUsers();
    }

}
