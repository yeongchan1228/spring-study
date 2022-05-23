package spring3.spring3study.trace.v2;

import org.junit.jupiter.api.Test;
import spring3.spring3study.trace.TraceStatus;

class TraceV2Test {

    @Test
    public void begin_end() throws Exception {
        TraceV2 traceV2 = new TraceV2();
        TraceStatus status = traceV2.begin("hello");
        TraceStatus status1 = traceV2.beginSync(status.getTraceId(), "hello1");
        traceV2.end(status1);
        traceV2.end(status);
    }

    @Test
    public void begin_exception() throws Exception {
        TraceV2 traceV2 = new TraceV2();
        TraceStatus status = traceV2.begin("hello");
        TraceStatus status1 = traceV2.beginSync(status.getTraceId(), "hello1");
        traceV2.exception(status1, new IllegalStateException());
        traceV2.exception(status, new IllegalStateException());
    }

}