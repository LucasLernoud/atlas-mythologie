package service.impl;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import repository.UserRepository;
import service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
