package spring2.spring2Study.order;

import spring2.spring2Study.discount.DiscountPolicy;
import spring2.spring2Study.discount.FixDiscountPolicy;
import spring2.spring2Study.discount.RateDiscountPolicy;
import spring2.spring2Study.member.Member;
import spring2.spring2Study.member.MemberRepository;
import spring2.spring2Study.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
