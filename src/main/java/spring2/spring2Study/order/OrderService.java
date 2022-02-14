package spring2.spring2Study.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
