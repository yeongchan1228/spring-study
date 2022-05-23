package spring3.spring3study.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.v2.TraceV2;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final TraceV2 traceV2;

    public void orderItem(String itemId, TraceStatus beforeStatus){
        TraceStatus status = null;
        try {
            status = traceV2.beginSync(beforeStatus.getTraceId(), "OrderService.orderItem()");
            orderRepository.save(itemId, status);
            traceV2.end(status);
        }catch (Exception e){
            traceV2.exception(status, e);
            throw e;
        }
    }
}
