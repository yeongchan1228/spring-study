package spring3.spring3study.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {

    private String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // UUID는 길이가 너무 길기 때문에. 앞에 8자리만 사용, 중복될 가능성은 존재한다.
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId(){
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId(){
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel(){
        return level == 0;
    }
}
