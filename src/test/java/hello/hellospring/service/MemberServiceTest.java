package hello.hellospring.service;

import hello.hellospring.domain.Member;

import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;//중복검사 이후 저장된 값을 클리어해주기위해 Reposityory를 불러와 다 끝나고나면 DB의 값을 날려준다.
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @BeforeEach//각 테스트를 실행하기전에 해준다.
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();//같은 memoryMemberRepository를 사용하기 위함 이렇게 memberReposiroty객체는 static이 사용되지 않는다면 문제가 된다. 이렇게 바꿔줌으로 의존성 주입을 한다.
        memberService = new MemberService(memberRepository);
    }
    @AfterEach//메서드가 끝날때마다 실행되는 함수
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given 상황이 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        //when 이것을 실행했을 때
        Long saveId = memberService.join(member);

        //then 이러한 결과가 나와야 한다.
        Member findNumber = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findNumber.getName());

    }
    @Test
    public void 중복_회원_예외(){
        //given 이런 상황이 주어졌을 때
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when 이것을 실행했을 때
        memberService.join(member1);//아래 join 로직을 실행하면 아래의 예외가 터지는지 검사(첫번째 인자에는 기대하는 예외 클래스, 두번째 인자에는 예외를 발생하는 코드를 작성
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//위 로직을 실행하면 뒤 예외타입과 같은지 혹은 터지는지 검사
        //then 이러한 결과가 나와야한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");//이러한 예외가 터지면(문자일치여부)

//        try{
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}