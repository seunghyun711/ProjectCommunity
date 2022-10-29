package ctoy.projectcommunity.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 로그인 시 사용되는 id
    private String pw; // 사용자 비밀번호
    private String email; // 사용자 이메일
}
