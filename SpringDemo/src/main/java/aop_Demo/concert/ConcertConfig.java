package aop_Demo.concert;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class ConcertConfig {
    //这里在没有@Component注解Audience的时候需要声明切面类用来创建一个实例
//    @Bean
//    public Audience audience() {
//        return new Audience();
//    }
}
