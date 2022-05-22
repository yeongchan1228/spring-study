package spring1.spring1Study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimpTraceAOP {

    @Around("execution(* spring1..*(..))") // 어디에 적용 시킬 것인지 타게팅
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis();

        System.out.println("Start : " + joinPoint.toString()); // 어떤 메소드가 실행되었는지 출력


        try {
            return joinPoint.proceed();

        } finally {
            long end = System.currentTimeMillis();
            long timeMs = end - start;

            System.out.println("End : " + joinPoint.toString() + "  " + timeMs + "ms"); // 어떤 메소드가 실행되었는지 출력

        }


    }
}
