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

}
