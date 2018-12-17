package ThreadDemo;

//有序交叉进行
//期待顺序 A 1，B 1,B 2,B 3,A 2,A 3
public class OrderCrossover {
    public static void demo(){
        Object lock=new Object();
        Thread A=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("A等待锁");
                synchronized (lock){
                    System.out.println("A得到锁");
                    System.out.println("A 1");
                   try {
                       //抛出锁并且等待
                       System.out.println("A进入等待状态，放弃锁的控制权");
                        lock.wait();
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                }
                System.out.println("A被唤醒");
                System.out.println("A 2");
                System.out.println("A 3");

            }
        });
        Thread B=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B等待锁");
                //阻塞在入口等待锁
                synchronized (lock) {
                    System.out.println("B得到锁");
                    System.out.println("B 1");
                    System.out.println("B 2");
                    System.out.println("B 3");
                    //通知
                    System.out.println("B打印完毕调用notify唤醒其他等待状态");
                    lock.notify();
                }
            }
        });
        A.start();
        B.start();
    }
    public static void main(String[] args) {
        demo();
    }
}
