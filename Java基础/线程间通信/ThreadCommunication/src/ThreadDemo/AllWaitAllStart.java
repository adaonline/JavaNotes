package ThreadDemo;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
//多个线程同时准备好，然后同时运行
public class AllWaitAllStart {
    private static void all_wait_start(){
        int num=3;
        CyclicBarrier cyclicBarrier=new CyclicBarrier(num);
        final Random random=new Random();
        for(char threadname='A';threadname<='C';threadname++){
            final String name=String.valueOf(threadname);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long prepareTime=random.nextInt(10000);
                    System.out.println(name+" 准备时间 "+prepareTime);
                    try {
                        Thread.sleep(prepareTime);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(name +"准备完毕，等待其他");
                        cyclicBarrier.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(name +"开始运行");
                }
            }).start();
        }

    }

    public static void main(String[] args) {
        all_wait_start();
    }
}
