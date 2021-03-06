package aop_Demo.aopWithinAnnotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= ConcertConfig.class)
public class PerformanceTest {

    @Autowired
    Performance performance;

    @Test
    public void acttest(){
        System.out.println("=====这是采用注解实现的切面=====");
        performance.perform();
    }

}
