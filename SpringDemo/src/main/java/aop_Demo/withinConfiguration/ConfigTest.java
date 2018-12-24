package aop_Demo.withinConfiguration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:config.xml")
@ContextConfiguration(locations = "classpath:config_around.xml")
public class ConfigTest {
    @Autowired
    Performance performance;

    @Test
    public void configTest(){
        System.out.println("=====这是采用配置文件的格式实现的切面=====");
        performance.perform();
    }
}
