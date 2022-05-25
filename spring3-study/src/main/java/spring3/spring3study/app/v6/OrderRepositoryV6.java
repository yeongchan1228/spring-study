package spring3.spring3study.app.v6;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring3.spring3study.trace.logtrace.ThreadLocalLogTrace;
import spring3.spring3study.trace.template.AbstractTemplate;
import spring3.spring3study.trace.template.callback.TraceTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV6 {

    private final TraceTemplate traceTemplate;

    public void save(String itemId){
        traceTemplate.execute("OrderRepository.save()", () -> {
            if(itemId.equals("ex")) throw new IllegalStateException("예외 발생!");
            sleep(1000);
            return null;
        });

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
