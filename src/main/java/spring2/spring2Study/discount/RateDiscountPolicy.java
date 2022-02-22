package spring2.spring2Study.discount;

import org.springframework.stereotype.Component;
import spring2.spring2Study.member.Grade;
import spring2.spring2Study.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy{

    private int discounPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discounPercent / 100;
        }
        else
            return 0;
    }
}
