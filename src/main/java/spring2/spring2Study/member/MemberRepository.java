package spring2.spring2Study.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

}
