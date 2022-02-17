package spring2.spring2Study;

import spring2.spring2Study.discount.RateDiscountPolicy;
import spring2.spring2Study.member.MemberService;
import spring2.spring2Study.member.MemberServiceImpl;
import spring2.spring2Study.member.MemoryMemberRepository;
import spring2.spring2Study.order.OrderService;
import spring2.spring2Study.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }
}
