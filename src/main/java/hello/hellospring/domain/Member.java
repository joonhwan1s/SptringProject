package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {//회원, 주문 등을 DB에 저장하고 관리

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 자동으로 데이터를 생성해주는 전략
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
