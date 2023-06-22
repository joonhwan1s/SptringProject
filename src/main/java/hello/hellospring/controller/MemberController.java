package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller//스프링 컨테이너에 MemberController 객체를 생성해서 스프링에 넣어두고 관리한다.
public class MemberController {//컨트롤러가 memberService를 통해서 회원가입하고 데이터를 조회할 수 있어야한다.
//Controller은 어차피 스프링이 관리하는 것이다.
    private final MemberService memberService;
    @Autowired// Autowired는 스프링 컨테이너에서 memberservice를 가져온다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }//생성자로 생성

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }

}//memberservice를 스프링이 스프링 컨테이너의 memberservice에 연결을 시켜준다.
//MemberController이 생성될때 스프링빈에 등록되어 있는 MemberService 객체를 넣어준다 == 의존성 객체
