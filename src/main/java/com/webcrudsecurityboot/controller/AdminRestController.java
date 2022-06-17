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
    public ResponseEntity<User> getUser(@PathVariable("id") Long id ) {
        return new ResponseEntity<>(userService.show(id), HttpStatus.OK);
        //return user;
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
    public ResponseEntity<User> updateUser(/*@PathVariable("id") Long id,*/ @RequestBody User user) {
//        HashSet<Role> roles = new HashSet<>();
//        for(Role role : roles) {
//            roles.add(role);
//        }
//        user.setRoles(roles);
     return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
       //return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser (@PathVariable Long id)  {
        userService.delete(id);
        //return "User with id: " + id + " was deleted";
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }
}
