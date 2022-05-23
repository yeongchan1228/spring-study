package spring3.spring3study.trace.logtrace;

import org.junit.jupiter.api.Test;
import spring3.spring3study.trace.TraceStatus;

class FieldLogTraceTest {

    FieldLogTrace fieldLogTrace = new FieldLogTrace();

    @Test
    public void begin_end() throws Exception {
        TraceStatus status = fieldLogTrace.begin("hello");
        TraceStatus status2 = fieldLogTrace.begin("hello2");
        fieldLogTrace.end(status2);
        fieldLogTrace.end(status);
    }

    @Test
    public void begin_exception() throws Exception {
        TraceStatus status = fieldLogTrace.begin("hello");
        TraceStatus status2 = fieldLogTrace.begin("hello2");

        fieldLogTrace.exception(status2, new IllegalStateException());
        fieldLogTrace.exception(status, new IllegalStateException());
    }
}