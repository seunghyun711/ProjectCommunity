package ctoy.projectcommunity.service;

import ctoy.projectcommunity.domain.Post;
import ctoy.projectcommunity.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시글 작성
    public void writePost(Post post) {
        postRepository.savePost(post);
    }

    // 특정 게시글 불러오기
    public Post findPost(Long id) {
        return postRepository.findById(id).get();
    }

    // 게시글 제목으로 불러오기
    public Optional<Post> postTitle(String title) {
        return postRepository.findByTitle(title);
    }
}
