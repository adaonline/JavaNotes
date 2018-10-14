package StreamTest.Reset;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class ResetDirectTest {
    public static void main(String[] args) {
        try {
            //创建出输出流
            PrintStream printStream=new PrintStream(new FileOutputStream("./src/StreamTest/Reset/[重定向]测试文件.txt"));
            //将程序的标准输出重定向到文件里
            System.setOut(printStream);
            System.out.print("这是重定向的内容，会出现在文件里！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
