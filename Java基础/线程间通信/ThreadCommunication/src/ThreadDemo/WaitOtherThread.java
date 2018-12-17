package ThreadDemo;

import java.util.concurrent.CountDownLatch;
//四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
public class WaitOtherThread {

    private static void start_D_After_ABC(){
        int worker=3;
        CountDownLatch countDownLatch=new CountDownLatch(worker);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("D 开始等待其他线程");
                try {
                    countDownLatch.await();
                    System.out.println("其他线程运行完成，D开始运行");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        for(char threadname='A';threadname<='C';threadname++){
            final String name=String.valueOf(threadname);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(name+"开始了");
                    try {
                        Thread.sleep(100);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(name+"结束了");
                    countDownLatch.countDown();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        start_D_After_ABC();
    }
}
