package ctoy.projectcommunity.repository;

import ctoy.projectcommunity.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository{
    Member save(Member member);

    Optional<Member> findById(Long id);
    List<Member> findAll();
    Optional<Member> findByName(String name);

}
