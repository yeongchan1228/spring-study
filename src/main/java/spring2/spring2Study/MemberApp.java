package spring2.spring2Study;

import spring2.spring2Study.member.*;

public class MemberApp { // 클라이언트 역할, 원래는 웹 페이지에서 클라이언트 역할을 수행함

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(member.getId());

        System.out.println("new Member : " + member.getName());
        System.out.println("findMember : " + findMember.getName());

    }
}
