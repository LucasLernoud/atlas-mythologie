package com.lucas.atlas_mythologie.repository;

import com.lucas.atlas_mythologie.model.Comment;
import com.lucas.atlas_mythologie.model.Myth;
import com.lucas.atlas_mythologie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMythId(Long mythId);

    List<Comment> findByUser(User user);

    List<Comment> findByMyth(Myth myth);
}
