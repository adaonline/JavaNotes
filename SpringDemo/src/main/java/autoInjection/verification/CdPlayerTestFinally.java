package autoInjection.verification;

import autoInjection.CDPlayerConfig;
import autoInjection.CompactDisc;
import autoInjection.MediaPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= CDPlayerConfig.class)
public class CdPlayerTestFinally {

    @Autowired
    private MediaPlayer player;

    @Autowired
    private CompactDisc cd;

    @Test
    public void cdNotNull(){
        assertNotNull(cd);
    }

    @Test
    public void player(){
        cd.play();
    }
}
