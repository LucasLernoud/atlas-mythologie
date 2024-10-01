package repository;

import model.Comment;
import model.Myth;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMythId(Long mythId);

    List<Comment> findByUser(User user);

    List<Comment> findByMyth(Myth myth);
}
