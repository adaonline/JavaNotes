package OnebyOne;
//如何让两个线程依次执行
public class OneByOneDemo {
    private static void demo1(){
        Thread A=new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });
        Thread B=new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("B");
            }
        });
        A.start();
        B.start();
    }
    private static void printNumber(String threadName){
        int i=0;
        for(;i<3;i++){
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(threadName+" thread:"+i);
        }

    }

    public static void main(String[] args) {
        demo1();
    }
}
