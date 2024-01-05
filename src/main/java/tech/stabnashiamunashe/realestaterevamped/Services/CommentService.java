package tech.stabnashiamunashe.realestaterevamped.Services;

import org.springframework.stereotype.Service;
import tech.stabnashiamunashe.realestaterevamped.Comment;
import tech.stabnashiamunashe.realestaterevamped.Repos.CommentsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;

    public CommentService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public void deleteComment(String id) {
        commentsRepository.deleteById(id);
    }

    public Optional<Comment> getCommentById(String id) {
        return commentsRepository.findById(id);
    }

    public Comment craeteComment(Comment comment) {
        return commentsRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        Comment existingComment = commentsRepository.findById(comment.getId()).orElse(null);
        assert existingComment != null;
        existingComment.setContent(comment.getContent());
        return commentsRepository.save(existingComment);
    }


    public List<Comment> getAllComments() {
        return commentsRepository.findAll();
    }
}
