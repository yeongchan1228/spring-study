package spring3.spring3study.trace.logtrace;

import org.junit.jupiter.api.Test;
import spring3.spring3study.trace.TraceStatus;

class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace threadLocalLogTrace = new ThreadLocalLogTrace();

    @Test
    public void begin_end() throws Exception {
        TraceStatus status = threadLocalLogTrace.begin("hello");
        TraceStatus status2 = threadLocalLogTrace.begin("hello2");
        threadLocalLogTrace.end(status2);
        threadLocalLogTrace.end(status);
    }

    @Test
    public void begin_exception() throws Exception {
        TraceStatus status = threadLocalLogTrace.begin("hello");
        TraceStatus status2 = threadLocalLogTrace.begin("hello2");

        threadLocalLogTrace.exception(status2, new IllegalStateException());
        threadLocalLogTrace.exception(status, new IllegalStateException());
    }
}