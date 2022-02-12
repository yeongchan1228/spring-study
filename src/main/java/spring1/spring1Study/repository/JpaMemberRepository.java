package spring1.spring1Study.repository;

import spring1.spring1Study.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // jpa가 테이블에 넣는 sql을 자동 생성해서 넣는다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member result = em.find(Member.class, id); // pk로 검색하기 떄문에 간단하게 찾기 가능
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
