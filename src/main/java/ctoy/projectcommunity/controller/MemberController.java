package ctoy.projectcommunity.controller;

import com.google.gson.JsonObject;
import ctoy.projectcommunity.domain.Member;
import ctoy.projectcommunity.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Controller
public class MemberController {

    public final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 가입
//    @GetMapping(value = "/project/join")
//    public String joinForm(){
//        return "project/newJoinForm";
//    }

    @PostMapping(value = "/project/join")
    @ResponseBody
    public String finishJoin(@RequestBody MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setPw(form.getPw());
        member.setEmail(form.getEmail());
        // 회원가입 시 중복된 아이디가 발생하면 예외처리 한다.
        try{
            memberService.write(member);
        /*
        실제로는 sqlintegrityconstraintviolationexception 예외가 발생하지만 이거는 DB에서의 예외이므로
        RuntimeException 으로 래핑하여 re throw 한다. 이런 경우 DataIntegrityViolationException를 사용한다.
         */
        }catch(DataIntegrityViolationException e) {
            JsonObject res = new JsonObject();
            res.addProperty("join_status", "overlap");
            return res.toString();
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject res = new JsonObject();
            res.addProperty("join_status", "unknownError");
            return res.toString();
        }

        JsonObject res = new JsonObject();
        res.addProperty("join_status", "success");
        return res.toString();
    }

//    @GetMapping(value = "/project/login")
//    public String loginForm() {
//        return "project/loginForm";
//    }

    // longinForm에서 입력한 정보를 받아 로그인 성패여부 결정.
    @PostMapping(value = "/project/login")
    @ResponseBody
    public String tryLogin(@RequestBody MemberForm form) {
        Optional<Member> tmp = memberService.memberName(form.getName());

        if(tmp.isEmpty()) { // 입력한 멤버가 없을 경우
            JsonObject res = new JsonObject();
            res.addProperty("login_status", "not exist");
            return res.toString();
        }
        else { // 입력한 멤버가 있을 경우
            Member get_member = tmp.get();
            if (form.getPw().equals(get_member.getPw())) { // 비밀번호 맞을 경우
                JsonObject res = new JsonObject();
                res.addProperty("login_status", "login success");
                return res.toString();
            }
            else { // 비밀번호 틀린 경우
                JsonObject res = new JsonObject();
                res.addProperty("login_status", "pw mismatch");
                return res.toString();
            }
        }
    }
}