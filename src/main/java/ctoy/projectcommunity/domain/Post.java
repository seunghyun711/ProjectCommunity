package ctoy.projectcommunity.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class Post {
    @Id @GeneratedValue
    private Long num;

    private Long post_id;

    private String title; // 게시글 제목

    private String content; // 게시글 내용

//    private LocalDateTime postDate; // 게시한 시간

    // 게시글
    @ManyToOne
    @JoinColumn
    private Member member;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

//    public LocalDateTime getPostDate() {
//        return postDate;
//    }
}
