package StreamTest.Reset;

import java.io.FileInputStream;
import java.util.Scanner;

public class ResetInTest {
    public static void main(String[] args) {
        try {
            //将文件重定向到系统in
            FileInputStream fileInputStream=new FileInputStream("./src/StreamTest/Reset/ResetInTest.java");
            System.setIn(fileInputStream);
            //用来获取输入
            Scanner sc=new Scanner( System.in);
            //将换行符作为分割
            sc.useDelimiter("\n");
            while (sc.hasNext()){
                System.out.println("输入的内容："+sc.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
