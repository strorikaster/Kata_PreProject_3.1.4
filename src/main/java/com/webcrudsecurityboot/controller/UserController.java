package com.webcrudsecurityboot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.webcrudsecurityboot.model.User;


    @Controller
    @RequestMapping("/user")
    public class UserController {

        @GetMapping
        public String userInfo(@AuthenticationPrincipal User user, Model model) {
            model.addAttribute("user", user);
            model.addAttribute("roles", user.getRoles());
            return "index";
        }
    }

