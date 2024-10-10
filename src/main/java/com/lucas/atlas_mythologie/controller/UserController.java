package com.lucas.atlas_mythologie.controller;

import com.lucas.atlas_mythologie.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import com.lucas.atlas_mythologie.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/login")
    public String login(@RequestBody  User user){
        return userService.verify(user);
    }

//    @GetMapping("/csrf")
//    public CsrfToken getCsrfToken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }
}
