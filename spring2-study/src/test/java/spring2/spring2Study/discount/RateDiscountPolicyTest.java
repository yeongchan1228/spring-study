package spring2.spring2Study.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring2.spring2Study.member.Grade;
import spring2.spring2Study.member.Member;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 되어야 한다.")
    void discount(){

        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);

        int discount = discountPolicy.discount(memberA, 10000);

        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인 적용 X")
    void notDiscount(){
        Long memberId = 2L;
        Member memberB = new Member(memberId, "memberB", Grade.BASIC);

        int discount = discountPolicy.discount(memberB, 10000);
        assertThat(discount).isEqualTo(0);
    }

}