package spring3.spring3study.trace.v1;

import org.junit.jupiter.api.Test;
import spring3.spring3study.trace.TraceStatus;

class TraceV1Test {
    
    @Test
    public void begin_end() throws Exception {
        TraceV1 traceV1 = new TraceV1();
        TraceStatus status = traceV1.begin("hello");
        traceV1.end(status);
    }

    @Test
    public void begin_exception() throws Exception {
        TraceV1 traceV1 = new TraceV1();
        TraceStatus status = traceV1.begin("hello");
        traceV1.exception(status, new IllegalStateException());
    }
}