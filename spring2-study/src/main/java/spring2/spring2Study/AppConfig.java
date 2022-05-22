package spring2.spring2Study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring2.spring2Study.discount.DiscountPolicy;
import spring2.spring2Study.discount.FixDiscountPolicy;
import spring2.spring2Study.discount.RateDiscountPolicy;
import spring2.spring2Study.member.MemberRepository;
import spring2.spring2Study.member.MemberService;
import spring2.spring2Study.member.MemberServiceImpl;
import spring2.spring2Study.member.MemoryMemberRepository;
import spring2.spring2Study.order.OrderService;
import spring2.spring2Study.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
