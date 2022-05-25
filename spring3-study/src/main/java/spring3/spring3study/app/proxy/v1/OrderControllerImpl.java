package spring3.spring3study.app.proxy.v1;

public class OrderControllerImpl implements OrderControllerV1{

    private final OrderServiceV1 orderService;

    public OrderControllerImpl(OrderServiceV1 orderServiceV1) {
        this.orderService = orderServiceV1;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
