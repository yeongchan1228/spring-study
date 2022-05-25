package spring3.spring3study.app.v6;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring3.spring3study.trace.logtrace.LogTrace;
import spring3.spring3study.trace.template.callback.TraceTemplate;

@RestController
@RequiredArgsConstructor
public class OrderControllerV6 {

    private final OrderServiceV6 orderService;
    private final TraceTemplate traceTemplate;

    @GetMapping("/v6/request")
    public String request(String itemId){
        return traceTemplate.execute("orderController.request()", () -> {
            orderService.orderItem(itemId);
            return "ok";
        });
    }
}
