package ctoy.projectcommunity.service;

import ctoy.projectcommunity.domain.Post;
import ctoy.projectcommunity.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<Post> postTitle(String title) {
        return postRepository.findByTitle(title);
    }

    // 전체 게시글을 리스트로 반환
    public List<Post> getPosts() {
        return postRepository.findAllPosts();
    }

    // 페이지 정보를 받아 게시글 반환
    public List<Post> getPostsByPage(int page, int limit) {
        return postRepository.findByPage(page, limit);
    }

    // 특정 id의 게시글 삭제
    public Optional<Post> deletePost(Long postId) {
        return postRepository.deleteById(postId);
    }

    // 특정 memberId의 게시글 목록 불러오기
    public List<Post> getMembersPosts(Long memberId) {
        return postRepository.findByMemberId(memberId);
    }

}
