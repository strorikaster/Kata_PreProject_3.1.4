package com.webcrudsecurityboot.controller;

import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.RoleService;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUsersController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public RestUsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



//    @GetMapping(value = "/api")
//    public String welcome() {
//        return "redirect:/api/users";
//    }

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAll() {
//        List<User> users = userService.getAllUsers();
////        model.addAttribute("allUsers", userService.getAllUsers());
////        model.addAttribute("roles", roleService.getAllRoles());
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @GetMapping("/users")
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

}
