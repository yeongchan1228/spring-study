package spring2.spring2Study.discount;

import spring2.spring2Study.member.Member;

public interface DiscountPolicy {

    /*
        @return : 할인 대상 금액
    */
    int discount(Member member, int price);
}
