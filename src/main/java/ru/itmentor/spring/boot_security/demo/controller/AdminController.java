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
        return "index";
    }

    @GetMapping("/create")
    public String getCreatePage(ModelMap modelMap) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        modelMap.addAttribute("authenticatedUser", authenticatedUser);
        modelMap.addAttribute("isCreate", true);
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("roles", roleService.findAllRoles());

        return "create";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        String redirectNotExistsTo = "/admin";
        User user = userService.getUserById(id);
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user == null) return "redirect:" + redirectNotExistsTo;

        modelMap.addAttribute("isCreate", false);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("authenticatedUser", authenticatedUser);
        modelMap.addAttribute("roles", roleService.findAllRoles());

        return "create";
    }

    @GetMapping("/details/{id}")
    public String getUserInfoPage(@PathVariable("id") Long id, ModelMap modelMap) {
        User user = userService.getUserById(id);
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        modelMap.addAttribute("user", user);
        modelMap.addAttribute("authenticatedUser", authenticatedUser);

        return "profile";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String createNewUser(
            @RequestParam("selected_roles") ArrayList<Long> selectedRolesID,
            @ModelAttribute("user") @Valid User user, BindingResult bindingResult
    ) {
        String redirectTo = "/admin/create";

        if (!bindingResult.hasErrors()) {
            user.addAuthorities(roleService.findRolesByIDs(selectedRolesID));
            userService.createUser(user);

            redirectTo = "/admin";
        }

        return "redirect:" + redirectTo;
    }

    @PostMapping("/edit/{id}")
    public String editUser(
            @PathVariable("id") Long id,
            @RequestParam("selected_roles") ArrayList<Long> selectedRolesID,
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult
    ) {
        String redirectTo = "redirect:/admin";

        if (!bindingResult.hasErrors()) {
            user.addAuthorities(roleService.findRolesByIDs(selectedRolesID));
            userService.updateUser(user);

            redirectTo = "redirect:/admin/details/" + id;
        }

        return redirectTo;
    }
}
