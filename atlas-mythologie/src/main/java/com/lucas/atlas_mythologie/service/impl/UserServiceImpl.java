package com.lucas.atlas_mythologie.service.impl;

import com.lucas.atlas_mythologie.dto.AuthResponseDTO;
import com.lucas.atlas_mythologie.model.Myth;
import com.lucas.atlas_mythologie.model.User;
import com.lucas.atlas_mythologie.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lucas.atlas_mythologie.repository.UserRepository;
import com.lucas.atlas_mythologie.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public Set<Myth> getFavoriteMyths(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isPresent()) {
            return userOpt.get().getFavoriteMyths();
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

//    @Override
//    public AuthResponseDTO verify(User user) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//
//        if (authentication.isAuthenticated()){
//            String token = jwtService.generateToken(user.getUsername());
//            return new AuthResponseDTO(token, user.getUsername(), user.getId());
//        }
//        return null;
//    }


    @Override
    public AuthResponseDTO verify(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(user.getUsername());

            // Gérer le cas où findByUsername retourne null
            User authenticatedUser = userRepository.findByUsername(user.getUsername());

            if (authenticatedUser == null) {
                throw new UsernameNotFoundException("Utilisateur non trouvé");
            }

            // Renvoyer la réponse avec le token, username et ID de l'utilisateur
            return new AuthResponseDTO(token, authenticatedUser.getUsername(), authenticatedUser.getId());
        }

        return null;
    }


}
