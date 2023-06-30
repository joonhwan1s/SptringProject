package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//스프링빈을 직접 등록하기위한 방법
public class SpringConfig {

      private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    private DataSource dataSource;
//
//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean//memberservice와 memberRepository를 둘다 스프링빈에 등록하고 spring bean에 등록되어 있는 memberRepository를 memberservice에 넣어준다.
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
    //스프링이 올라올때 memberservice를 스프링 컨테이너에 올리고 memberRepositroy도 Bean을 보고 스프링빈에 올려준다. 그러면서 memberservice에 memberRepositroy를 사용할 수 있도록 호출해준다.
//    @Bean
//    public MemberRepository memberRepository(){

        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }
}
