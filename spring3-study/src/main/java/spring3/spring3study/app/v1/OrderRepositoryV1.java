package spring3.spring3study.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.v1.TraceV1;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final TraceV1 traceV1;

    public void save(String itemId){
        TraceStatus status = null;
        try {
            status = traceV1.begin("OrderRepository.save()");
            // 저장 로직
            if(itemId.equals("ex")) throw new IllegalStateException("예외 발생!");
            sleep(1000);
            traceV1.end(status);
        }catch (Exception e){
            traceV1.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
