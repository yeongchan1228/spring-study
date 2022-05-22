package spring2.spring2Study.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring2.spring2Study.discount.FixDiscountPolicy;
import spring2.spring2Study.member.Grade;
import spring2.spring2Study.member.Member;
import spring2.spring2Study.member.MemberRepository;
import spring2.spring2Study.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {


    @Test
    void createOrder(){

        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "A", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

        Order order = orderService.createOrder(1L, "ItemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}