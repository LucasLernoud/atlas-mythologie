package com.lucas.atlas_mythologie.service;

import com.lucas.atlas_mythologie.dto.AuthResponseDTO;
import com.lucas.atlas_mythologie.model.Myth;
import com.lucas.atlas_mythologie.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public interface UserService {


    public Optional<User> getUserById(Long id);

    public User createUser(User user);

    public User findByUsername(String username);

    AuthResponseDTO verify(User user);

    Set<Myth> getFavoriteMyths(Long userId);
}
