package spring1.spring1Study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring1.spring1Study.domain.Member;
import spring1.spring1Study.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional // JPA를 사용하기 위해서, 데이터 베이스에 접근하는 class에 붙여야 한다.
public class MemberService {
    private final MemberRepository memberRepository;

    //@Autowired // DI 의존관계 설정
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*  회원가입  */
    public Long join(Member member){

        // 같은 이름 회원 X
        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*  전체 회원 조회  */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
