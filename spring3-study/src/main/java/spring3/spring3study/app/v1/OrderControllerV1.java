package spring3.spring3study.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring3.spring3study.app.v0.OrderServiceV0;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.v1.TraceV1;

@RestController
@RequiredArgsConstructor
public class  OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final TraceV1 traceV1;

    @GetMapping("/v1/request")
    public String request(String itemId){
        TraceStatus status = null;
        try {
            status = traceV1.begin("OrderController.request()");
            orderService.orderItem(itemId);
            traceV1.end(status);
        }catch (Exception e){
            traceV1.exception(status, e);
            // 예외를 먹어버리기 때문에 예외를 던져주어야 한다.
            throw e;
        }
        return "ok";
    }
}
