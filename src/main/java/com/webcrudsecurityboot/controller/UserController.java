package com.webcrudsecurityboot.controller;

import com.webcrudsecurityboot.service.RoleService;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.webcrudsecurityboot.model.User;


@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String start() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "show";
    }

    @GetMapping("/admin")
//    public String index(Model model) {
//        User thisUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        model.addAttribute("thisUser", thisUser);
//        return "index";
//    }
    public String allUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "index";
    }
}


