package tech.stabnashiamunashe.realestaterevamped.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.stabnashiamunashe.realestaterevamped.Models.Comment;

import java.util.List;

public interface CommentsRepository extends MongoRepository<Comment, String> {
    List<Comment> findByUser_Email(String email);
}
