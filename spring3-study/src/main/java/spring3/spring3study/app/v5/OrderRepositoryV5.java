package spring3.spring3study.app.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.ThreadLocalLogTrace;
import spring3.spring3study.trace.template.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV5 {

    private final ThreadLocalLogTrace logTrace;

    public void save(String itemId){

        AbstractTemplate<Void> abstractTemplate = new AbstractTemplate<>(logTrace) {
            @Override
            public Void call() {
                if(itemId.equals("ex")) throw new IllegalStateException("예외 발생!");
                sleep(1000);
                return null;
            }
        };
        abstractTemplate.execute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
