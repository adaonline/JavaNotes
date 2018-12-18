package autoInjection;

import org.springframework.stereotype.Component;

@Component
public class SgtPepprts implements CompactDisc {
    public void play() {
        System.out.println("playering");
    }
}
