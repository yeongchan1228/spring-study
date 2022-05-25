package spring3.spring3study.app.trace.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring3.spring3study.trace.logtrace.ThreadLocalLogTrace;
import spring3.spring3study.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final ThreadLocalLogTrace logTrace;

    public void orderItem(String itemId){
        AbstractTemplate<Void> abstractTemplate = new AbstractTemplate<>(logTrace) {
            @Override
            public Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };
        abstractTemplate.execute("OrderService.orderItem()");
    }
}
