package ctoy.projectcommunity.controller;

import ctoy.projectcommunity.domain.Member;
import ctoy.projectcommunity.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    public MemberService memberService;

    @GetMapping(value = "/project/join")
    public String joinForm(){
        return "project/newJoinForm";
    }

    @PostMapping(value = "/project/finish")
    public String finishJoin(Member member){
        memberService.write(member);
        return "redirect:/";
    }

    @GetMapping(value = "/project/login")
    public String loginForm() {
        return "project/loginForm";
    }

    // longinForm에서 입력한 정보를 받아 로그인 성패여부 결정.
    @PostMapping(value = "/project/tryLogin")
    public String tryLogin(Member member) {
        Member tmp = memberService.memberInfo(member.getId());
        if(tmp == null) {
            return "redirect:/project/loginForm";
        }
        else if (tmp.getId() == member.getId()) {
            // 아직 페이지 안만듦. 로그인 성공시 글 목록으로 이동.
            return "project/viewList";
        }
        else {
            return "redirect:/project/loginForm";
        }

    }

}
