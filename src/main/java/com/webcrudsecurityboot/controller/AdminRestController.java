package com.webcrudsecurityboot.controller;

import com.webcrudsecurityboot.ExceptionHandler.NoSuchUserException;
import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.RoleService;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class AdminRestController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping("/users")
    public  List<User> getAllUsers() {
        return userService.getAllUsers();
    }

//    @GetMapping(value = "/users")
//    public List<User> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return users;
//    }

    @GetMapping("/user")
    public User getPrincipalUser() {
        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return thisUser;
    }


    @GetMapping("/users/{id}")
    public User getUsers(@PathVariable Long id) {
        User user = userService.show(id);

        if (user == null) {
            throw new NoSuchUserException("There is no user with ID =  " + id + " in Database");
        }
        return user;
    }


//    @PostMapping("/users")
//    public User addNewUser(@RequestBody User user, @RequestParam Long[] rolesId) {
//        HashSet<Role> roles = new HashSet<>();
//        for(Long roleId : rolesId) {
//            roles.add(roleService.show(roleId));
//        }
//        user.setRoles(roles);
//        userService.save(user);
//        return user;
//    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }


    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.update(user);
        return user;
    }


    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.show(id);

        if (user == null) {
            throw new NoSuchUserException("There is no user with ID = " + id + " in Database");
        }
        userService.delete(id);
        return "User with ID " + id + " was delete";
    }


    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

}
