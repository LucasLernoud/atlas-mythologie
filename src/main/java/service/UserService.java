package service;

import model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {


    public Optional<User> getUserById(Long id);

    public User createUser(User user);

    public Optional<User> findByUsername(String username);
}
