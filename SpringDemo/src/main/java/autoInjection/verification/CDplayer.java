package autoInjection.verification;

import autoInjection.CompactDisc;
import autoInjection.MediaPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//影碟机组件
@Component
public class CDplayer implements MediaPlayer {
    private CompactDisc cd;

    @Autowired
    public CDplayer(CompactDisc cd) {
        this.cd = cd;
    }

    public void play(){
        cd.play();
    }
}
