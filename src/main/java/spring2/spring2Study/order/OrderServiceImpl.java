package spring2.spring2Study.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring2.spring2Study.discount.DiscountPolicy;
import spring2.spring2Study.discount.FixDiscountPolicy;
import spring2.spring2Study.discount.RateDiscountPolicy;
import spring2.spring2Study.member.Member;
import spring2.spring2Study.member.MemberRepository;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; // final -> 무조건 값이 존재해야 한다. 값이 불변이다.
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy;

    
    /*@RequiredArgsConstructor가 이 부분을 생성해준다. final이 붙은 필드에 해당하는 생성자*/
   /* @Autowired // ac.getBean(MemberRepository.class)...
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    /*DiscountPolicy의 빈이 두 개일 경우 필드명으로 선택 주입할 수 있다.*/
   /* @Autowired // ac.getBean(MemberRepository.class)...
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }*/

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
