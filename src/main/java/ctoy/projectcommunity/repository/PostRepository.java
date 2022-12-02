package ctoy.projectcommunity.repository;

import ctoy.projectcommunity.domain.Member;
import ctoy.projectcommunity.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface PostRepository {
    Post savePost(Post post);

    Optional<Post> findById(Long id);
    List<Post> findByTitle(String title);
    List<Post> findAllPosts();
    Optional<Post> deleteById(Long id);

}
