package spring2.spring2Study.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring2.spring2Study.member.Grade;
import spring2.spring2Study.member.Member;
import spring2.spring2Study.member.MemberService;
import spring2.spring2Study.member.MemberServiceImpl;

public class OrderServiceTest {

    private final OrderService orderService = new OrderServiceImpl();
    private final MemberService memberService = new MemberServiceImpl();

    @Test
    public void order(){
        Long memberId = 1L;

        Member member = new Member(memberId, "member1", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(member.getId(), "itemA", 10000);
        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
    }

}
