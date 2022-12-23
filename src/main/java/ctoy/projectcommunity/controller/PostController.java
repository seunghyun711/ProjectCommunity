package ctoy.projectcommunity.controller;

import com.google.gson.JsonObject;
import ctoy.projectcommunity.domain.Post;
import ctoy.projectcommunity.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 작성
    // 게시글 작성
    @PostMapping(value = "project/write")
    @ResponseBody
    public String writePost(@RequestBody Post post) {

        System.out.println(post.getTitle());
        System.out.println(post.getContent());
        try {
            postService.writePost(post);
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject res = new JsonObject();
            res.addProperty("post_status", "error");
            return res.toString();
        }
        JsonObject res = new JsonObject();
        res.addProperty("post_status","success");
        return res.toString();
    }

    // 게시글 수정
    @PutMapping("/project/write/modify/{post_id}")
    @ResponseBody
    public String postUpdate(@PathVariable("post_id") Long id, @RequestBody Post post) {
        Post postTmp = postService.findPost(id);
        if(postTmp == null) {
            JsonObject res = new JsonObject();
            res.addProperty("post_update", "error");
            return res.toString();
        }

        postTmp.setTitle(post.getTitle());
        postTmp.setContent(post.getContent());

        try {
            postService.writePost(postTmp);
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject res = new JsonObject();
            res.addProperty("post_update", "error");
        }
        JsonObject res = new JsonObject();
        res.addProperty("post_update","success");
        return res.toString();
    }


    // 페이지 정보 받아서 게시글 리스트 불러오기
    @GetMapping(value="project/post/posts_with_page")
    @ResponseBody
    public Object getPostListWithPage(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        List<PostForm> postformList = new ArrayList<>();
        try {
            List<Post> postList = postService.getPostsByPage(page, limit);
            for(Post p:postList) {
                postformList.add(new PostForm(p.getPost_id(), p.getCreate_member_id(), p.getTitle()));
            }
            return postformList;
        } catch (Exception e) {
            // 리스트 불러오는 중 오류 발생 시
            e.printStackTrace();
            JsonObject res = new JsonObject();
            res.addProperty("get_AllPostList_status","error");
            return res.toString();
        }
    }

    // 게시글 삭제
    @DeleteMapping(value="project/post/deletePost/{deletePostId}")
    @ResponseBody
    public String deletePost(@PathVariable("deletePostId") Long deletePostId) {
        Optional<Post> result = postService.deletePost(deletePostId);
        JsonObject res = new JsonObject();
        if(result.isEmpty()) {
            res.addProperty("delete_post_status","error");
        }
        else {
            res.addProperty("delete_post_status", "success");
        }
        return res.toString();
    }

    // 내가 쓴 글 목록
    @GetMapping(value = "project/post/getMembersPostList/{member_id}")
    @ResponseBody
    public Object getMembersPostList(@PathVariable("member_id") Long member_id) {
        List<PostForm> resList = new ArrayList<>();
        try {
            List<Post> membersPostList = postService.getMembersPosts(member_id);
            for(Post p:membersPostList) {
                resList.add(new PostForm(p.getPost_id(), p.getCreate_member_id(), p.getTitle()));
            }
            return resList;
        } catch (Exception e) {
            // 리스트 불러오는 중 오류 발생 시
            e.printStackTrace();
            JsonObject res = new JsonObject();
            res.addProperty("get_MembersPostList_status","error");
            return res.toString();
        }
    }


    // 게시글 검색(게시글 제목 기준으로 검색)
    @GetMapping(value = "project/post/search/{title}")
    @ResponseBody
    public Object searchPost(@PathVariable("title") String postTitle) {
        List<Post> postTmp = postService.postTitle(postTitle);
        if (postTmp.isEmpty()) {
            JsonObject res = new JsonObject();
            res.addProperty("search_post_status","not exist");
            return res.toString();
        }else{
            return postTmp;
        }
    }

    // 게시글 상세정보
    @GetMapping(value = "project/post/getPostDetail/{post_id}")
    @ResponseBody
    public Object getPostDetail(@PathVariable("post_id") Long postId) {
        try {
            Post resPost = postService.findPost(postId);
            return resPost;
        } catch (NoSuchElementException nee) {
            JsonObject res = new JsonObject();
            res.addProperty("get_post_detail_status","post not exist");
            return res.toString();
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject res = new JsonObject();
            res.addProperty("get_post_detail_status","error");
            return res.toString();
        }
    }

    static class PostForm {
        Long post_id;
        Long create_member_id;
        String title;

        public PostForm(Long post_id, Long create_member_id, String title) {
            this.post_id = post_id;
            this.create_member_id = create_member_id;
            this.title = title;
        }

        public Long getPost_id() {
            return post_id;
        }

        public Long getCreate_member_id() {
            return create_member_id;
        }

        public String getTitle() {
            return title;
        }
    }
}
