package aop_Demo.aopWithinAnnotation;

import org.aspectj.lang.annotation.*;

//@Aspect
//@Component
public class Audience_Convenience {
    //定义且命名一个切点
    @Pointcut("execution(** aop_Demo.aopWithinAnnotation.Performance.perform(..))")
    public void performance() {};
    //表演前
    @Before("performance()")
    public void silenceCellPhones(){
        System.out.println("手机静音");
    }

    //表演前
    @Before("performance()")
    public void takeSeats(){
        System.out.println("找座位");
    }
    //表演后
    @AfterReturning("performance()")
    public void applause(){
        System.out.println("鼓掌啪啪啪！");
    }

    //表演失败后
    @AfterThrowing("performance()")
    public void demandRefund(){
        System.out.println("要求退款");
    }
}
