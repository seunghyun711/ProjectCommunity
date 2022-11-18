package ctoy.projectcommunity.repository;

import ctoy.projectcommunity.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;


public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Optional<Member> findByName(String name) {
        try {
            Member member = em.createQuery("select m from Member m where m.name = :name", Member.class)
                    .setParameter("name", name).getSingleResult();
            return Optional.ofNullable(member);
        } catch (NoResultException e) {
            return Optional.ofNullable(null);
        }
    }
}