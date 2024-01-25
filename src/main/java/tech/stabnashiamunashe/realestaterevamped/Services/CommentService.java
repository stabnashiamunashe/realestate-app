package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.Models.Comment;
import tech.stabnashiamunashe.realestaterevamped.Repos.CommentsRepository;
import tech.stabnashiamunashe.realestaterevamped.Security.Service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;

    private final UserService userService;

    public CommentService(CommentsRepository commentsRepository, UserService userService) {
        this.commentsRepository = commentsRepository;
        this.userService = userService;
    }

    public void deleteComment(String id) {
        commentsRepository.deleteById(id);
    }

    public Optional<Comment> getCommentById(String id) {
        return commentsRepository.findById(id);
    }

    public Comment createComment(Comment comment, Authentication authentication) {
        userService.getUserByEmail(authentication.getName()).ifPresent(comment::setUser);
        return commentsRepository.save(comment);
    }

    public Comment updateComment(Comment comment , Authentication authentication) {

        Comment existingComment = commentsRepository.findById(comment.getId()).orElse(null);
        userService.getUserByEmail(authentication.getName()).ifPresent(// CHECK IF USER IS THE OWNER OF THE COMMENT
                user -> {
                    if (existingComment != null) {
                        if (existingComment.getUser().getId().equals(user.getId())) {
                            existingComment.setContent(comment.getContent());
                        }
                    }
                }
        );

        assert existingComment != null;
        return commentsRepository.save(existingComment);
    }


    public List<Comment> getAllComments() {
        return commentsRepository.findAll();
    }

    public List<Comment> getAllCommentsForUser(String email) {
        return commentsRepository.findByUser_Email(email);
    }
}
