package spring3.spring3study.trace.template;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.LogTrace;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

    private final LogTrace logTrace;

    public T execute(String message){
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);

            T result = call();

            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            // 예외를 먹어버리기 때문에 예외를 던져주어야 한다.
            throw e;
        }
    }

    protected abstract T call();
}
