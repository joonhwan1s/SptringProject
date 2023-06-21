package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//@Repository//스프링빈에 등록한다.(구현체로 있음)(Controller을 통해서 외부 요청을 받고 Service에서 비지니스 로직을 만들고 Repository에서 데이터를 저장한다.)
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();//
    private static long sequence = 0L;//0,1,2 키값을 생성해주는 것

    @Override
    public Member save(Member member) {
        member.setId(++sequence);//키값을 하나 올려주고 store에 ID값을 셋팅해준다.
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))//name이랑 같은것을 다 거른다.
                .findAny();//하나라도 찾아서 같은게 있으면 값을 반환해버린다. 끝까지 돌리는데 없으면 Option에 null이 포함돼서 반환한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//Store에 있는 values가 member 들이다. 이것을 반환한다.
    }

    public void clearStore(){//저장소가 중복뒤지 않게 날려준다.
        store.clear();
    }

}
