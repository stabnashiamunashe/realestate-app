package tech.stabnashiamunashe.realestaterevamped.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tech.stabnashiamunashe.realestaterevamped.Models.Comment;
import tech.stabnashiamunashe.realestaterevamped.Services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, Authentication authentication) {
        Comment savedComment = commentService.createComment(comment, authentication);
        return new ResponseEntity<>(savedComment, org.springframework.http.HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        var comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, Authentication authentication) {
        Comment updatedComment = commentService.updateComment(comment, authentication);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllCommentsForLoggedInUser(Authentication authentication) {
        List<Comment> comments = commentService.getAllCommentsForUser(authentication.getName());
        return ResponseEntity.ok(comments);
    }



}
