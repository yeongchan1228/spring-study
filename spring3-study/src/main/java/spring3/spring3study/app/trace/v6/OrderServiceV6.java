package spring3.spring3study.app.trace.v6;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring3.spring3study.trace.template.callback.TraceTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV6 {

    private final OrderRepositoryV6 orderRepository;
    private final TraceTemplate traceTemplate;

    public void orderItem(String itemId){
        traceTemplate.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
