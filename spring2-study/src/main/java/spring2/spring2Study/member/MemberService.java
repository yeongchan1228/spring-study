package spring2.spring2Study.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
