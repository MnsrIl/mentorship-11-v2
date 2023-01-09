package ru.itmentor.Task11.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.Task11.model.Role;
import ru.itmentor.Task11.service.RoleService;
import ru.itmentor.Task11.service.UserService;
import ru.itmentor.Task11.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> usersList = userService.getUsers();
        System.out.println("Getting users list " + usersList);
        return new ResponseEntity<>(usersList, HttpStatus.FOUND);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(roleService.findAllRoles(), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        userService.createUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        userService.updateUser(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
