package ctoy.projectcommunity.service;

import ctoy.projectcommunity.domain.Member;
import ctoy.projectcommunity.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 정보 입력
    public void write(Member member){
        memberRepository.save(member);
    }

    // 회원 목록
    public List<Member> memberList(){
        return memberRepository.findAll();
    }

    // 특정 회원 불러오기
    public Member memberInfo(Long id){
        return memberRepository.findById(id).get();
    }

    // 회원 아이디로 불러오기
    public Member memberName(String name) {
        Member res = memberRepository.findByName(name).get();
        System.out.println(res.getName());
        return res;
 //       return memberRepository.findByName(name).get();
    }

    // 회원 삭제
//    public void memberDelete(Long id){
//        memberRepository.deleteById(id);
 //   }
}
