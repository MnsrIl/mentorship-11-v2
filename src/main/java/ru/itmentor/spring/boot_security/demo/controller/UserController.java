package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmentor.spring.boot_security.demo.model.User;

@Controller
public class UserController {
    @GetMapping("/user")
    public String getUserPage(ModelMap modelMap) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        modelMap.addAttribute("user", authenticatedUser);
        modelMap.addAttribute("authenticatedUser", authenticatedUser);

        return "profile";
    }
}
