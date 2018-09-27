package StreamTest;

import java.io.FileInputStream;

public class FileInputStreamTest {
    public static void main(String[] args) throws Exception {
        //读取文件创建字节流
        FileInputStream fileInputStream=new FileInputStream("./src/StreamTest/FileInputStreamTest.java");
        //创建读取的通道
        byte[] buff=new byte[10];

        int readed=0;
        //循环读取，读取一次往后移动一次指针
        while ((readed=fileInputStream.read(buff))>0){
            System.out.println(new String(buff,0,readed));

        }
        fileInputStream.close();
    }
}
