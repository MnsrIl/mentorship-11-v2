package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getUsersPage(ModelMap modelMap) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        modelMap.addAttribute("authenticatedUser", authenticatedUser);
        modelMap.addAttribute("users", userService.getUsers());
        modelMap.addAttribute("roles", roleService.findAllRoles());
        modelMap.addAttribute("user", new User());

        return "admin-page";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String createNewUser(
            @RequestParam("form_selected_roles") ArrayList<Long> selectedRolesID,
            @ModelAttribute("user") @Valid User user, BindingResult bindingResult
    ) {
        String redirectTo = "/admin";

        if (!bindingResult.hasErrors()) {
            user.addAuthorities(roleService.findRolesByIDs(selectedRolesID));
            userService.createUser(user);

            redirectTo = "/admin";
        }

        return "redirect:" + redirectTo;
    }

    @PostMapping("/edit")
    public String editUser(
            @RequestParam("form_selected_roles") ArrayList<Long> selectedRolesID,
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult
    ) {
        String redirectTo = "redirect:/admin";

        if (!bindingResult.hasErrors()) {
            user.addAuthorities(roleService.findRolesByIDs(selectedRolesID));
            userService.updateUser(user);
        }

        return redirectTo;
    }
}
