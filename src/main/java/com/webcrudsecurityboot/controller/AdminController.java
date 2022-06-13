package com.webcrudsecurityboot.controller;

import com.webcrudsecurityboot.model.Role;
import com.webcrudsecurityboot.model.User;
import com.webcrudsecurityboot.service.RoleService;
import com.webcrudsecurityboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/admin")
    public String returnAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "index";
    }
//    @GetMapping(value = "/")
//    public String start(){
//        return "redirect:/login";
//    }
//
//    @GetMapping(value = "/login")
//    public String login() {
//            return "login";
//    }

//    @GetMapping(value = "/admin")
//    public String welcome() {
//        return "redirect:/admin/all";
//    }
//
//    @GetMapping(value = "admin/all")
//    public String allUsers(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("user", user);
//        model.addAttribute("allUsers", userService.getAllUsers());
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "111";
//    }
//
//    @GetMapping("admin/{id}")
//    public String show(@AuthenticationPrincipal User user, @PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.show(user.getId()));
//        model.addAttribute("role", roleService.show(user.getId()));
//        return "show";
//    }
//
//    @GetMapping(value = "admin/add")
//    public String addUser(Model model,
//                          @ModelAttribute("user") User user,
//                          @ModelAttribute("role") Role role) {
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "new";
//    }
//
//    @PostMapping(value = "admin/add")
//    public String postAddUser(@ModelAttribute("user") User user,
//                              @RequestParam("rolesSelected") Long[] rolesId,
//                              BindingResult bindingResult) {
//        if(bindingResult.hasErrors()) {
//            return "new";
//        }
//        HashSet<Role> roles = new HashSet<>();
//        for(Long roleId : rolesId) {
//            roles.add(roleService.show(roleId));
//        }
//
//        user.setRoles(roles);
//        userService.save(user);
//        return "redirect:/admin";
//    }
//
//
//    @GetMapping(value = "admin/{id}/edit")
//    public String editUser(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.show(id));
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "edit";
//    }
//
//    @PatchMapping("admin/{id}")
//    public String update(@ModelAttribute("user") User user,
//                         @RequestParam("rolesSelected") Long[] rolesId
//
//    ) {
//        Set<Role> roles = new HashSet();
//        for(Long roleId : rolesId) {
//            roles.add(roleService.show(roleId));
//        }
//        user.setRoles(roles);
//        userService.update(user);
//        return "redirect:/admin/all";
//    }
//
//    @DeleteMapping("admin/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin";
//    }
//
//    @GetMapping(value = "/user")
//    public String getUserPage(Model model, @AuthenticationPrincipal User user) {
//        model.addAttribute("user", user);
//        return "show";
//    }
}
