package StreamTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileOutputStreamTest {
    public static void main(String[] args) {
        try {
            //输入流读取
            FileInputStream fileInputStream=new FileInputStream("./src/StreamTest/FileOutputStreamTest.java");
            //输出流写出
            FileOutputStream fileOutputStream=new FileOutputStream("thisFile.txt");
            byte[] buf=new byte[20];
            int readed=0;
            //循环读取并且写入到文件里
            while ((readed=fileInputStream.read(buf))>0){
                fileOutputStream.write(buf,0,readed);
            }
            fileInputStream.close();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
