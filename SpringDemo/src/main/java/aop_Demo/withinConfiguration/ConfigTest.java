package aop_Demo.withinConfiguration;


import aop_Demo.aopWithinAnnotation.Performance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "config.xml")
public class ConfigTest {
    @Autowired
    Performance performance;

    @Test
    public void configTest(){
        performance.perform();
    }
}
