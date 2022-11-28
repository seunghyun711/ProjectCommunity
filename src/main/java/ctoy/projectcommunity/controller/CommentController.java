package ctoy.projectcommunity.controller;

import com.google.gson.JsonObject;
import ctoy.projectcommunity.domain.Comment;
import ctoy.projectcommunity.service.CommentService;
import ctoy.projectcommunity.service.MemberService;
import ctoy.projectcommunity.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final MemberService memberService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService, MemberService memberService) {
        this.commentService = commentService;
        this.postService = postService;
        this.memberService = memberService;
    }

    // 댓글 작성
    @PostMapping(value = "project/comment/write/{post_id}")
    @ResponseBody
    public String writeComment(@PathVariable("post_id") Long id, @RequestBody Comment comment) {

        try {
            commentService.writeComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject res = new JsonObject();
            res.addProperty("comment_status", "error");
            return res.toString();
        }
        JsonObject res = new JsonObject();
        res.addProperty("comment_status", "success");
        return res.toString();
    }

    // 댓글 불러오기
    @GetMapping(value = "project/comment/getCommentList/{post_id}")
    @ResponseBody
    public Object commentList(@PathVariable("post_id") Long id) {
        List<Comment> commentsTmp = commentService.commentList(id);
        if (commentsTmp.isEmpty()){
            JsonObject res = new JsonObject();
            res.addProperty("commentsList_status","empty");
            return res.toString();
        }else{
            return commentsTmp;
        }

//        try {
//            return commentsTmp;
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            JsonObject res = new JsonObject();
//            res.addProperty("commentList_status", "error");
//            return res.toString();
//        }
    }
}

