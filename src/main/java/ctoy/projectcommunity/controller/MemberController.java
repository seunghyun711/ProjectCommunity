package ctoy.projectcommunity.controller;

import com.google.gson.JsonObject;
import ctoy.projectcommunity.domain.Member;
import ctoy.projectcommunity.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Controller
public class MemberController {
    public MemberService memberService;

    // 회원 가입
//    @GetMapping(value = "/project/join")
//    public String joinForm(){
//        return "project/newJoinForm";
//    }

    @PostMapping(value = "/project/finish")
    @ResponseBody
    public String finishJoin(Member member){
        // 회원가입 시 중복된 아이디가 발생하면 예외처리 한다.
        try{
            memberService.write(member);
        /*
        실제로는 sqlintegrityconstraintviolationexception 예외가 발생하지만 이거는 DB에서의 예외이므로
        RuntimeException 으로 래핑하여 re throw 한다. 이런 경우 DataIntegrityViolationException를 사용한다.
         */
        }catch(DataIntegrityViolationException e){
            JsonObject res = new JsonObject();
            res.addProperty("overlap", "true");
            return res.toString();
        }
        return "redirect:/";
    }

    @GetMapping(value = "/project/login")
    public String loginForm() {
        return "project/loginForm";
    }

    // longinForm에서 입력한 정보를 받아 로그인 성패여부 결정.
    @PostMapping(value = "/project/tryLogin")
    public String tryLogin(Member member, Model model) {
        Optional<Member> tmp = memberService.memberName(member.getName());

        if(tmp.isEmpty()) { // 입력한 멤버가 없을 경우
            return "redirect:/project/login";
        }
        else { // 입력한 멤버가 있을 경우
            Member get_member = tmp.get();
            if (member.getPw().equals(get_member.getPw())) { // 비밀번호 맞을 경우
                return "redirect:/";
            }
            else { // 비밀번호 틀린 경우
                return "project/joinError";
            }
        }
    }
}