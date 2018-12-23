package aop_Demo.aopWithinAnnotation.withInParam;

import aop_Demo.aopWithinAnnotation.ConcertConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= TrackCounterConfig.class)
public class TrackCountTest {
    @Autowired
    private CompactDisc cd;

    @Autowired
    private TrackCounter counter;

    @Test
    public void test(){
        Assert.assertNotNull(cd);
    }

    @Test
    public void testTrackCounter(){
        cd.playTrack(0);
        cd.playTrack(1);
        cd.playTrack(2);
        cd.playTrack(2);
        //第三个磁道被播放了两遍
        System.out.println(counter.getPlayCount(1));
        System.out.println(counter.getPlayCount(2));
        Assert.assertEquals(1,counter.getPlayCount(1));
        Assert.assertEquals(2,counter.getPlayCount(2));
    }
}
