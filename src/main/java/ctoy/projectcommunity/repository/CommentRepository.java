package ctoy.projectcommunity.repository;

import ctoy.projectcommunity.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment saveComment(Comment comment);

    Optional<Comment> findById(Long id);

    List<Comment> findCommentsListById(Long id);
}
