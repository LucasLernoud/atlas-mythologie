package service.impl;

import model.Comment;
import model.Myth;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CommentRepository;
import repository.MythRepository;
import repository.UserRepository;
import service.CommentService;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MythRepository mythRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment saveComment(String content, Long userId, Long mythId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Myth> mythOpt = mythRepository.findById(mythId);

        if (userOpt.isPresent() && mythOpt.isPresent()) {
            User user = userOpt.get();
            Myth myth = mythOpt.get();

            Comment comment = new Comment();
            comment.setContent(content);
            comment.setUser(user);
            comment.setMyth(myth);

            return commentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("User or Myth not found");
        }
    }

    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Comment not found");
        }
    }


    public List<Comment> findCommentsByUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return commentRepository.findByUser(userOpt.get());
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public List<Comment> findCommentsByMyth(Long mythId) {
        Optional<Myth> mythOpt = mythRepository.findById(mythId);
        if (mythOpt.isPresent()) {
            return commentRepository.findByMyth(mythOpt.get());
        } else {
            throw new IllegalArgumentException("Myth not found");
        }
    }
}