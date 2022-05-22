package spring1.spring1Study.domain;

import javax.persistence.*;

@Entity
public class Member {
    
    @Id // PK 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@Column(name = "username") 테이블에 컬럼명이 username으로 설정되어 있을 때
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
