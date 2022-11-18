package ctoy.projectcommunity.repository;

import ctoy.projectcommunity.domain.Member;
import ctoy.projectcommunity.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface PostRepository {
    Post savePost(Post post);

//    Optional<Member> findById(Long id);
    Optional<Post> findByTitle(String title);
    List<Post> findAllPosts();

}
