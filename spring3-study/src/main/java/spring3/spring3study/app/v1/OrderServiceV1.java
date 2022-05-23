package spring3.spring3study.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring3.spring3study.app.v0.OrderRepositoryV0;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.v1.TraceV1;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final TraceV1 traceV1;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try {
            status = traceV1.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            traceV1.end(status);
        }catch (Exception e){
            traceV1.exception(status, e);
            throw e;
        }
    }
}
