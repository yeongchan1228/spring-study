package spring3.spring3study.config.proxy.v1_proxy.concrete_proxy;

import spring3.spring3study.app.proxy.v2.OrderControllerV2;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

    private final OrderControllerV2 target;
    private final LogTrace logTrace;

    public OrderControllerConcreteProxy(OrderControllerV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderController.request()");

            String result = target.request(itemId);

            logTrace.end(status);

            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog(String itemId) {
        return target.noLog(itemId);
    }
}
