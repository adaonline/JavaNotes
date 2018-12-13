package Generic;

import java.util.Random;

public class Test {
    public static void main(String[] args) throws Exception{
        System.out.println("芋圆开始抽奖！！！");
        Random random=new Random();
        int m=0;
        int k=0;
        for(int j=0;j<100;j++){
           // Thread.sleep(100);
            int i=random.nextInt(100);
            if(i<=25){
                System.out.print("中奖,");
                k++;
            }else{
                System.out.print("完蛋,");
            }
            m++;
            if(m>=10){
                System.out.println("");
                m=0;
            }

        }
        System.out.println("芋圆抽100中奖"+k+"次");

    }
}
