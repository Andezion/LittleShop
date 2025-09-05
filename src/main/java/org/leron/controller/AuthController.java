package org.leron.controller;

import org.leron.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController
{
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login()
    {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("registrationError", null);
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email,
                               Model model) {
        try
        {
            userService.registerNewUser(username, password, email);
            return "redirect:/login?registered";
        }
        catch (RuntimeException e)
        {
            model.addAttribute("registrationError", e.getMessage());
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            return "auth/register";
        }
    }

    @GetMapping("/user/profile")
    public String userProfile()
    {

        return "user/profile";

    }
}