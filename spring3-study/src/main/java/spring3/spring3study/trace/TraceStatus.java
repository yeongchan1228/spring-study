package spring3.spring3study.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TraceStatus { // 로그 시작 정보를 가지고 있다.

    private TraceId traceId;
    private Long startTimeMs;
    private String message;
}
