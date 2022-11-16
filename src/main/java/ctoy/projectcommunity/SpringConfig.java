package ctoy.projectcommunity;

import ctoy.projectcommunity.repository.JpaMemberRepository;
import ctoy.projectcommunity.repository.JpaPostRepository;
import ctoy.projectcommunity.repository.MemberRepository;
import ctoy.projectcommunity.repository.PostRepository;
import ctoy.projectcommunity.service.MemberService;
import ctoy.projectcommunity.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public PostService postService(){
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository() {
        return new JpaPostRepository(em);
    }
}
