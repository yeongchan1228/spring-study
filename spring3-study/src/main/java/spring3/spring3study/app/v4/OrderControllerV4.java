package spring3.spring3study.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.ThreadLocalLogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final ThreadLocalLogTrace logTrace;

    @GetMapping("/v4/request")
    public String request(String itemId){
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            logTrace.end(status);
        }catch (Exception e){
            logTrace.exception(status, e);
            // 예외를 먹어버리기 때문에 예외를 던져주어야 한다.
            throw e;
        }
        return "ok";
    }
}
