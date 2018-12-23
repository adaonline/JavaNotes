package aop_Demo.aopWithinAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//环绕切面
@Aspect
@Component
public class Audience_around {
    @Pointcut("execution(** aop_Demo.aopWithinAnnotation.Performance.perform(..))")
    public void performance() {}

    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint jp) {
        try {
            System.out.println("手机静音Around");
            jp.proceed();
            System.out.println("鼓掌鼓掌Around");
        } catch (Throwable e) {
            System.out.println("要求退款Around");
        }
    }

}
