package com.lucas.atlas_mythologie.repository;

import com.lucas.atlas_mythologie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
