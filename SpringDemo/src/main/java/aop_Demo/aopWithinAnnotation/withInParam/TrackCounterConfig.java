package aop_Demo.aopWithinAnnotation.withInParam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAspectJAutoProxy
public class TrackCounterConfig {
    @Bean
    public CompactDisc blackDisc(){
        BlankDisc blankDisc=new BlankDisc();
        blankDisc.setTitle("title");
        blankDisc.setArtist("artist");
        List<String> tracks=new ArrayList<String>();
        tracks.add("A");
        tracks.add("B");
        tracks.add("C");
        blankDisc.setTracks(tracks);
        return blankDisc;
    }

    @Bean
    public TrackCounter trackCounter(){
        return new TrackCounter();
    }
}
