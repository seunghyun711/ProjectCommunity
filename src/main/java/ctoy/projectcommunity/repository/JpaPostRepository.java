package ctoy.projectcommunity.repository;

import ctoy.projectcommunity.domain.Member;
import ctoy.projectcommunity.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;


public class JpaPostRepository implements PostRepository{

    private final EntityManager em;

    public JpaPostRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Post savePost(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        Post post = em.find(Post.class, id);
        return Optional.ofNullable(post);
    }

//    @Override
//    public Optional<Member> findById(Long id) {
//        Member member = em.find(Member.class, id);
//        return Optional.ofNullable(member);
//    }

    @Override
    public Optional<Post> findByTitle(String title) {
        try {
            Post post = em.createQuery("select m from Post m where m.title = :title", Post.class)
                    .setParameter("title", title).getSingleResult();
            return Optional.ofNullable(post);
        } catch (NoResultException e) {
            return Optional.ofNullable(null);
        }
    }

    @Override
    public List<Post> findAllPosts() {
        return em.createQuery("select m from Post m", Post.class)
                .getResultList();
    }
}
