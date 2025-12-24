package com.example.iot.controller;

import com.example.iot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam String username, 
                                @RequestParam String password, 
                                @RequestParam String confirmPassword, // Nouveau paramètre récupéré du formulaire
                                Model model) {
        
        // 1. Vérification : Les mots de passe correspondent-ils ?
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Les mots de passe ne correspondent pas.");
            return "signup";
        }

        try {
            // 2. Appel au service si la validation est correcte
            userService.registerUser(username, password);
            return "redirect:/login"; 
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}