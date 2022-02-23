package spring2.spring2Study.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring2.spring2Study.discount.DiscountPolicy;
import spring2.spring2Study.discount.FixDiscountPolicy;
import spring2.spring2Study.discount.RateDiscountPolicy;
import spring2.spring2Study.member.Member;
import spring2.spring2Study.member.MemberRepository;

@Service
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; // final -> 무조건 값이 존재해야 한다.
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy;

    @Autowired // ac.getBean(MemberRepository.class)...
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
