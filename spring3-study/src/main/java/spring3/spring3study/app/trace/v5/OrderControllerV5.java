package spring3.spring3study.app.trace.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring3.spring3study.trace.logtrace.LogTrace;
import spring3.spring3study.trace.template.AbstractTemplate;

@RestController
@RequiredArgsConstructor
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final LogTrace logTrace;

    @GetMapping("/v5/request")
    public String request(String itemId){
        AbstractTemplate<String> abstractTemplate = new AbstractTemplate<>(logTrace) {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return abstractTemplate.execute("orderController.request()");
    }
}
