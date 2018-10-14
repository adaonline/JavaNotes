package StreamTest.Process;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class WriterToProcess {
    public static void main(String[] args) {
        try {
            //执行对应的类
            Process p =Runtime.getRuntime().exec("java GetAndOutPrint");
            //对子进程创建输入流，这个流对当前程序来说是输出流
            PrintStream printStream=new PrintStream(p.getOutputStream());
            printStream.println("这是java程序向子进程写入的输入流");
            printStream.println("这是java程序向子进程写入的输入流");

            printStream.println("这是java程序向子进程写入的输入流");

            printStream.println("这是java程序向子进程写入的输入流");

            printStream.println("这是java程序向子进程写入的输入流");

            printStream.println("这是java程序向子进程写入的输入流");

            printStream.println("这是java程序向子进程写入的输入流");

            System.exit(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
class GetAndOutPrint{
    public static void main(String[] args) {
        try {
            System.out.println("打印标记");
            Scanner in=new Scanner(System.in);
            PrintStream printStream=new PrintStream(new FileOutputStream("./src/StreamTest/Process/WriterToProcess测试文件.txt"));
            in.useDelimiter("\n");
            while (in.hasNext()){
                printStream.println(in.next());
//                System.out.println("子进程获取到的内容是："+in.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}