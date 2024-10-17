package com.lucas.atlas_mythologie.controller;

import com.lucas.atlas_mythologie.dto.CommentDTO;
import com.lucas.atlas_mythologie.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lucas.atlas_mythologie.service.CommentService;
import com.lucas.atlas_mythologie.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.findAllComments();
        return ResponseEntity.ok(comments);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findCommentById(id);
        return comment.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/myth/{mythId}")
    public ResponseEntity<List<Comment>> getCommentsByMyth(@PathVariable Long mythId) {
        List<Comment> comments = commentService.findCommentsByMyth(mythId);
        return ResponseEntity.ok(comments);
    }

    /**
     * Save a new comment
     * @param commentDTO CommentDTO object containing the comment details
     * @return the saved comment
     */
    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody CommentDTO commentDTO) {
        try {
            Comment savedComment = commentService.saveComment(commentDTO.getContent(), commentDTO.getUserId(), commentDTO.getMythId());
            return ResponseEntity.ok(savedComment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
