package ctoy.projectcommunity.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    private Long comment_post_id;

    private Long comment_member_id;

    private String comment; // 댓글 내용

    public void setComment_post_id(Long comment_post_id) {
        this.comment_post_id = comment_post_id;
    }

    public void setComment_member_id(Long comment_member_id) {
        this.comment_member_id = comment_member_id;
    }

    public Long getComment_post_id() {
        return comment_post_id;
    }

    public Long getComment_member_id() {
        return comment_member_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
