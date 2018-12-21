package aop_Demo.concert;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;

//标注为切面,只不过切点表达式太频繁，优化在Audience_Convenience
//@Aspect
//@Component
public class Audience {
    //表演前
    @Before("execution(** aop_Demo.concert.Performance.perform(..))")
    public void silenceCellPhones(){
        System.out.println("手机静音");
    }

    //表演前
    @Before("execution(** aop_Demo.concert.Performance.perform(..))")
    public void takeSeats(){
        System.out.println("找座位");
    }
    //表演后
    @AfterReturning("execution(** aop_Demo.concert.Performance.perform(..))")
    public void applause(){
        System.out.println("鼓掌啪啪啪！");
    }

    //表演失败后
    @AfterThrowing("execution(** aop_Demo.concert.Performance.perform(..))")
    public void demandRefund(){
        System.out.println("要求退款");
    }
}
