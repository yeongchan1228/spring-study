package spring3.spring3study.app.trace.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.ThreadLocalLogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final ThreadLocalLogTrace logTrace;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            logTrace.end(status);
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
