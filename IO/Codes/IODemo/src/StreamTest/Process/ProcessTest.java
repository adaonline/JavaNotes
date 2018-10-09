package StreamTest.Process;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessTest {
    public static void main(String[] args) {
        try {
            //运行javac
            Process p=Runtime.getRuntime().exec("javac");
            //用错误流创建Reader对象，这个reader对程序本身是输入流，对javac进程是输出流
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String buf=null;
            while ((buf=bufferedReader.readLine())!=null){
                System.out.print(buf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
