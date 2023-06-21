package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller//스프링 컨테이너에 MemberController 객체를 생성해서 스프링에 넣어두고 관리한다.
public class MemberController {//컨트롤러가 memberService를 통해서 회원가입하고 데이터를 조회할 수 있어야한다.
//Controller은 어차피 스프링이 관리하는 것이다.
    private final MemberService memberService;
    @Autowired// Autowired는 스프링 컨테이너에서 memberservice를 가져온다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }//생성자로 생성
}//memberservice를 스프링이 스프링 컨테이너의 memberservice에 연결을 시켜준다.
//MemberController이 생성될때 스프링빈에 등록되어 있는 MemberService 객체를 넣어준다 == 의존성 객체
