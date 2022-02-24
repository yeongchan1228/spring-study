package spring2.spring2Study.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import spring2.spring2Study.member.Grade;
import spring2.spring2Study.member.Member;

@Component
//@Primary // 동일 빈에 대한 우선 순위를 준다.
//@Qualifier("mainDiscountPolicy") // 별명 느낌 DiscountPolicy 클래스의 빈이 2개일 경우 Qualifier로 구분 가능하다.
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
