package aop_Demo.aopWithinAnnotation.withInParam;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

//定义一个磁道计算的切面
@Aspect
public class TrackCounter {
    private Map<Integer,Integer> trackCounts=new HashMap<Integer, Integer>();
    //args(trackNumber)表明传递给playTrack方法的int类型也会传递到通知中去，这参数是被通知方法的
    @Pointcut("execution(* aop_Demo.aopWithinAnnotation.withInParam.CompactDisc.playTrack(int))&& args(trackNumber)")
    public void trackPlayed(int trackNumber){}

    //用来记录某个磁道被播放的次数
    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber){
        int currentCount=getPlayCount(trackNumber);
        trackCounts.put(trackNumber,currentCount+1);
    }

    public int getPlayCount(int trackNumber){
        return trackCounts.containsKey(trackNumber) ? trackCounts.get(trackNumber):0;
    }
}
