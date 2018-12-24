package aop_Demo.withinConfiguration;

import org.aspectj.lang.ProceedingJoinPoint;

public class Audience {
    public void silenceCellPhones() {
        System.out.println("手机静音");
    }

    public void applause() {
        System.out.println("鼓掌");
    }

    public void demandRefund() {
        System.out.println("要求退款");
    }

    public void watchPerformance(ProceedingJoinPoint pd){
        try {
            System.out.println("手机静音");
            pd.proceed();
            System.out.println("鼓掌");
        }catch (Throwable e){
            System.out.println("要求退款");
        }

    }
}
