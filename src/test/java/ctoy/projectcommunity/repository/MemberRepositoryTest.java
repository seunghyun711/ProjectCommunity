package ctoy.projectcommunity.repository;

import ctoy.projectcommunity.domain.Member;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setId(1L);
        member1.setName("spring1");
        member1.setPw("1234");
        member1.setEmail("wow");
        memberRepository.save(member1);

        Member result = memberRepository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }
}
