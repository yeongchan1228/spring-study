package spring3.spring3study.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        // Target을 찾아서 실행한다.
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        log.info("TimeProxy 종료 - 실행 시간 = {}", endTime - startTime);
        return result;
    }
}
