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
import java.util.List;
import java.util.Set;

@Controller
@RestController
@RequestMapping("/api")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



//    @GetMapping(value = "/admin")
//    public String welcome() {
//        return "redirect:/admin/all";
//    }

    @GetMapping(value = "/users")
//    public String allUsers(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("user", user);
//        model.addAttribute("allUsers", userService.getAllUsers());
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "index";
    public List<User> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }

//    @GetMapping("admin/{id}")
//    public String show(@AuthenticationPrincipal User user, @PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.show(user.getId()));
//        model.addAttribute("role", roleService.show(user.getId()));
//        return "show";
//    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.show(id);
        return user;
    }

//    @GetMapping(value = "admin/add")
//    public String addUser(Model model,
//                          @ModelAttribute("user") User user,
//                          @ModelAttribute("role") Role role) {
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "new";
//    }



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

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user, @RequestParam Long[] rolesId) {
        HashSet<Role> roles = new HashSet<>();
        for(Long roleId : rolesId) {
            roles.add(roleService.show(roleId));
        }
        user.setRoles(roles);
        userService.save(user);
        return user;
    }

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

//    @DeleteMapping("admin/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin";
//    }

      @DeleteMapping("/users/{id}")
    public String deleteUser (@PathVariable Long id)  {
        userService.delete(id);
        return "User with id: " + id + " was deleted";
      }


//    @GetMapping(value = "/user")
//    public String getUserPage(Model model, @AuthenticationPrincipal User user) {
//        model.addAttribute("user", user);
//        return "show";
//    }
}
