package spring3.spring3study.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.v2.TraceV2;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final TraceV2 traceV2;

    public void save(String itemId, TraceStatus beforeStatus){
        TraceStatus status = null;
        try {
            status = traceV2.beginSync(beforeStatus.getTraceId(), "OrderRepository.save()");
            // 저장 로직
            if(itemId.equals("ex")) throw new IllegalStateException("예외 발생!");
            sleep(1000);
            traceV2.end(status);
        }catch (Exception e){
            traceV2.exception(status, e);
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
