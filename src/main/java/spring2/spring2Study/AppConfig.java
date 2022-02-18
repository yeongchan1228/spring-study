package spring2.spring2Study;

import spring2.spring2Study.discount.DiscountPolicy;
import spring2.spring2Study.discount.FixDiscountPolicy;
import spring2.spring2Study.discount.RateDiscountPolicy;
import spring2.spring2Study.member.MemberRepository;
import spring2.spring2Study.member.MemberService;
import spring2.spring2Study.member.MemberServiceImpl;
import spring2.spring2Study.member.MemoryMemberRepository;
import spring2.spring2Study.order.OrderService;
import spring2.spring2Study.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
