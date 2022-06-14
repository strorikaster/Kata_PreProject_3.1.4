package com.webcrudsecurityboot.controller;

import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.RoleService;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
//    @GetMapping(value = "/users")
//    public List<User> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return users;
//    }

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable Long id ) {
        User user = userService.show(id);
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
    public void newUser(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user, @RequestParam Long[] rolesId) {
        HashSet<Role> roles = new HashSet<>();
        for(Long roleId : rolesId) {
            roles.add(roleService.show(roleId));
        }
        user.setRoles(roles);
        userService.save(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser (@PathVariable Long id)  {
        userService.delete(id);
        return "User with id: " + id + " was deleted";
    }
}
