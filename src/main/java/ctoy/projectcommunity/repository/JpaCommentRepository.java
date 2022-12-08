package ctoy.projectcommunity.repository;

import ctoy.projectcommunity.domain.Comment;
import ctoy.projectcommunity.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaCommentRepository implements CommentRepository{

    private final EntityManager em;

    public JpaCommentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment saveComment(Comment comment) {
        em.persist(comment);
        return comment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        Comment comment = em.find(Comment.class, id);
        return Optional.ofNullable(comment);
    }

    @Override
    public List<Comment> findCommentsListById(Long post_id) {
        return em.createQuery("select m from Comment m where m.comment_post_id = :id", Comment.class)
                .setParameter("id",post_id).getResultList();
    }

    @Override
    public Optional<Comment> deleteByCommentId(Long id){
        try {
            Comment findComment = em.find(Comment.class, id); // 없으면 findPost는 null임.
            em.remove(findComment);
            return Optional.ofNullable(findComment);
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }

}
