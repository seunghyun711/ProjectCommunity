package ctoy.projectcommunity.controller;

import ctoy.projectcommunity.domain.Member;
import ctoy.projectcommunity.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
public class MemberController {
    @Autowired
    public MemberService memberService;

    // 회원 가입
    @GetMapping(value = "/project/join")
    public String joinForm(){
        return "project/newJoinForm";
    }

    @PostMapping(value = "/project/finish")
    public String finishJoin(Member member){
        // 회원가입 시 중복된 아이디가 발생하면 예외처리 한다.
        try{
            memberService.write(member);
        /*
        실제로는 sqlintegrityconstraintviolationexception 예외가 발생하지만 이거는 DB에서의 예외이므로
        RuntimeException 으로 래핑하여 re throw 한다. 이런 경우 DataIntegrityViolationException를 사용한다.
         */
        }catch(DataIntegrityViolationException e){
            System.out.println("중복된 아이디");
            return "project/joinError";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/project/login")
    public String loginForm() {
        return "project/loginForm";
    }

    // longinForm에서 입력한 정보를 받아 로그인 성패여부 결정.
    @PostMapping(value = "/project/tryLogin")
    public String tryLogin(Member member) {
        Member tmp = memberService.memberName(member.getName());
        System.out.println(tmp.getName());
        if(tmp == null) {
            return "redirect:/project/login";
        }
        else if (tmp.getName().equals(member.getName())) {
            // 아직 페이지 안만듦. 로그인 성공시 글 목록으로 이동.
            return "redirect:/";
        }
        else {
            return "redirect:/project/login";
        }

    }
}
