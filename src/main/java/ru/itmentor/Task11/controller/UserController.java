package ru.itmentor.Task11.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itmentor.Task11.model.User;

@Controller
public class UserController {
    @GetMapping("api/auth")
    @ResponseBody
    public ResponseEntity<User> getUserPage(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @GetMapping
    public String getPage() {
        return "index";
    }
}
