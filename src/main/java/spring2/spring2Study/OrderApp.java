package spring2.spring2Study;

import spring2.spring2Study.member.*;
import spring2.spring2Study.order.Order;
import spring2.spring2Study.order.OrderService;
import spring2.spring2Study.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Member memberA = new Member(1L, "memberA", Grade.VIP);

        memberService.join(memberA);

        Order order = orderService.createOrder(memberA.getId(), "itemA", 10000);

        System.out.println("order : " + order);
        System.out.println("orderPrice : " + order.calculatePrice());

    }

}
