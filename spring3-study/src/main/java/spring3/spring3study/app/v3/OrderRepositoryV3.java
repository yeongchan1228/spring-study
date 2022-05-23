package spring3.spring3study.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.LogTrace;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace logTrace;

    public void save(String itemId){
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.save()");
            // 저장 로직
            if(itemId.equals("ex")) throw new IllegalStateException("예외 발생!");
            sleep(1000);
            logTrace.end(status);
        }catch (Exception e){
            logTrace.exception(status, e);
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
