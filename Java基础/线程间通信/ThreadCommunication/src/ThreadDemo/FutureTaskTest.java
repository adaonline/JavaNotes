package ThreadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
//子线程给主线程返回结果
public class FutureTaskTest {
    private static void returnResultTomain(){
        Callable callable=new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("任务开始");
                Thread.sleep(1000);
                int result=0;
                for(int i=0;i<=100;i++){
                    result+=i;
                }
                System.out.println("任务完成并且返回");
                return result;
            }
        };
        FutureTask futureTask=new FutureTask<>(callable);
        new Thread(futureTask).start();
        try {
            System.out.println("获取结果前。。。");
            System.out.println("结果："+futureTask.get());
            System.out.println("获取结果后。。。");
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        returnResultTomain();
    }
}
