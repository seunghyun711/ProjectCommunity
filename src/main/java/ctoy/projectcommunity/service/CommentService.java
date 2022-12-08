package ctoy.projectcommunity.service;

import ctoy.projectcommunity.domain.Comment;
import ctoy.projectcommunity.repository.CommentRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // 댓글 작성
    public void writeComment(Comment comment){
        commentRepository.saveComment(comment);
    }

    // 댓글 목록
    public List<Comment> commentList(Long id) {
        return commentRepository.findCommentsListById(id);
    }

    // 특정 댓글 불러오기
    public Comment findComment(Long id) {
        return commentRepository.findById(id).get();
    }

    // 특정 id의 댓글 삭제
    public Optional<Comment> deleteComment(Long commentId) {
        return commentRepository.deleteByCommentId(commentId);
    }

}
