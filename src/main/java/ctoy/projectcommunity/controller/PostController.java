package ctoy.projectcommunity.controller;

import com.google.gson.JsonObject;
import ctoy.projectcommunity.domain.Post;
import ctoy.projectcommunity.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        res.addProperty("post_id", postTmp.getPost_id());
        res.addProperty("post_title", postTmp.getTitle());
        res.addProperty("post_content",postTmp.getContent());
        return res.toString();
    }
}
