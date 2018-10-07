package StreamTest;

import java.io.FileOutputStream;
import java.io.PrintStream;
//处理流输出文件
public class PrintoutStreamTest {
    public static void main(String[] args) {
        try {
            FileOutputStream f=new FileOutputStream("PrintStream测试文件.txt");
            PrintStream ps=new PrintStream(f);
            ps.println("输出普通字符串");
            ps.println(new PrintoutStreamTest());
            //关闭处理流的时候，也会关闭自己包装的底层流
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
