package ctoy.projectcommunity.repository;

import ctoy.projectcommunity.domain.Member;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired // 테스트 코드에서 @Autowired가 없으면 빈에서 가져오지 못함
    private MemberRepository memberRepository;

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        member1.setPw("1234");
        member1.setEmail("wow");
        memberRepository.save(member1);

        Member result = memberRepository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }
}