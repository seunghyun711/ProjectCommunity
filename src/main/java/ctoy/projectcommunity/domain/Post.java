package ctoy.projectcommunity.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id @GeneratedValue
    private Long post_id;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name="create_member_id")
//    private Member createMember;

    private Long create_member_id;

    private String title; // 게시글 제목

    private String content; // 게시글 내용

//    private LocalDateTime postDate; // 게시한 시간


    public Long getPost_id() {
        return post_id;
    }

    // 게시글
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreate_member_id() {
        return create_member_id;
    }

    public void setCreate_member_id(Long create_member_id) {
        this.create_member_id = create_member_id;
    }

    //    public Member getCreateMember() {
//        return createMember;
//    }
//
//    public void setCreateMember(Member createMember) {
//        this.createMember = createMember;
//    }


    //    public LocalDateTime getPostDate() {
//        return postDate;
//    }
}
