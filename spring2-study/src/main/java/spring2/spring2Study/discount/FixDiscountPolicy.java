package spring2.spring2Study.discount;

import org.springframework.stereotype.Component;
import spring2.spring2Study.annotation.MainDiscountPolicy;
import spring2.spring2Study.member.Grade;
import spring2.spring2Study.member.Member;

@Component
@MainDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP) //enum 타입은 == 가능
            return discountFixAmount;
        else
            return 0;
    }
}
