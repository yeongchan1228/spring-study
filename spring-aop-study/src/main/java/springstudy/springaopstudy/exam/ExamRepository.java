package springstudy.springaopstudy.exam;

import org.springframework.stereotype.Repository;
import springstudy.springaopstudy.exam.annotation.Retry;
import springstudy.springaopstudy.exam.annotation.Trace;

@Repository
public class ExamRepository {

    private static int seq = 0;

    /**
     * 5번에 한 번 실패
     */
    @Trace
    @Retry(value = 4)
    public String save(String itemId){
        seq++;
        if(seq % 5 == 0){
            throw new IllegalStateException("에외 발생");
        }
        return "ok";
    }
}
