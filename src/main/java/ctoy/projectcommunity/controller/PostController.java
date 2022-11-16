package ctoy.projectcommunity.controller;

import com.google.gson.JsonObject;
import ctoy.projectcommunity.domain.Post;
import ctoy.projectcommunity.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 작성
    @PostMapping(value = "project/write")
    @ResponseBody
    public String writePost(@RequestBody Post post) {
        Post createPost = new Post();
        createPost.setTitle(post.getTitle());
        createPost.setContent(post.getContent());

        try {
            postService.writePost(post);
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject res = new JsonObject();
            res.addProperty("post_status", "error");
            return  res.toString();
        }
        JsonObject res = new JsonObject();
        res.addProperty("post_status","success");
        return res.toString();
    }
}
