package com.lucas.atlas_mythologie.service;

import com.lucas.atlas_mythologie.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {

    public List<Comment> findAllComments();

    public Optional<Comment> findCommentById(Long id);

    public Comment saveComment(String content, Long userId, Long mythId);

    public void deleteComment(Long id);

    public List<Comment> findCommentsByUser(Long userId);

    public List<Comment> findCommentsByMyth(Long mythId);
}
