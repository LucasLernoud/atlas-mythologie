package com.lucas.atlas_mythologie.controller;

import com.lucas.atlas_mythologie.dto.AuthResponseDTO;
import com.lucas.atlas_mythologie.dto.RegisterRequestDTO;
import com.lucas.atlas_mythologie.model.Myth;
import com.lucas.atlas_mythologie.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import com.lucas.atlas_mythologie.service.UserService;

import java.util.Optional;
import java.util.Set;

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
    public ResponseEntity<?> createUser(@RequestBody RegisterRequestDTO registerRequest) {

        // Vérification que les deux mots de passe correspondent
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Les mots de passe ne sont pas identiques.");
        }

        if (userService.isUsernameTaken(registerRequest.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Nom d'utilisateur déjà pris");
        }

        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(registerRequest.getPassword());

        userService.createUser(newUser);
        return ResponseEntity.ok("Utilisateur créé avec succès");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        AuthResponseDTO authResponse = userService.verify(user);

        if (authResponse != null) {
            return ResponseEntity.ok(authResponse);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
    }


    @GetMapping("/favoritemyths/{id}")
    public ResponseEntity<Set<Myth>> getFavoriteMyths(@PathVariable Long idUser){

        Set<Myth> myths = userService.getFavoriteMyths(idUser);
        return ResponseEntity.ok(myths);

    }

//    @GetMapping("/csrf")
//    public CsrfToken getCsrfToken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute("_csrf");
//    }
}
