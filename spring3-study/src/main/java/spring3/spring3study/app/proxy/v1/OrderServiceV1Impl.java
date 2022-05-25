package spring3.spring3study.app.proxy.v1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceV1Impl implements OrderServiceV1{

    private final OrderRepositoryV1 orderRepository;

    public OrderServiceV1Impl(OrderRepositoryV1 orderRepositoryV1) {
        this.orderRepository = orderRepositoryV1;
    }

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
