package spring3.spring3study.config.proxy.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import spring3.spring3study.app.proxy.v1.OrderRepositoryV1;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1 target;
    private final LogTrace logTrace;

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.save()");

            target.save(itemId);

            logTrace.end(status);
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }

    }
}
