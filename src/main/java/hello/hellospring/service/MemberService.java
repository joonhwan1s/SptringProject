package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//@Service//이것을 넣어줌으로 MemberController로부터 호출 받을 수 있도록 만들어준다
public class MemberService {
//순수한 자바코드이기 때문에 스프링이 가져오지 못한다. 그러기 위해서는 @Service를 등록해줘야 한다.
    private final MemberRepository memberRepository;
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }//Autowired가 있는 것을 보고 위에 Service가 있는것을 보고 스프링 컨테이너에 MemberRepository를 넣어준다
    //memberRepository를 외부에서 넣어준다. 이것을 의존성 주입이라고 한다.
    //회원가입
    public Long join(Member member){//validateDuplicateMember 메소드의 내용을 리팩토링하여 추출
        //같은 이름 중복회원은 안된다.
        validateDuplicateMember(member);//중복회원 검증하고 통과하면 저장한다.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원 입니다.");
            });
    }

    public List<Member> findMembers() {//전체 회원 조회
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
