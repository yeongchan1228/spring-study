package spring3.spring3study.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.v2.TraceV2;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final TraceV2 traceV2;

    @GetMapping("/v2/request")
    public String request(String itemId){
        TraceStatus status = null;
        try {
            status = traceV2.begin("OrderController.request()");
            orderService.orderItem(itemId, status);
            traceV2.end(status);
        }catch (Exception e){
            traceV2.exception(status, e);
            // 예외를 먹어버리기 때문에 예외를 던져주어야 한다.
            throw e;
        }
        return "ok";
    }
}
