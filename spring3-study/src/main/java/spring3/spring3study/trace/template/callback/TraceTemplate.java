package spring3.spring3study.trace.template.callback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring3.spring3study.trace.TraceStatus;
import spring3.spring3study.trace.logtrace.LogTrace;

@Slf4j
@RequiredArgsConstructor
public class TraceTemplate {

    private final LogTrace logTrace;

    public <T> T execute(String message, TraceCallBack<T> callBack){
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);

            T result = callBack.callBack();

            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
