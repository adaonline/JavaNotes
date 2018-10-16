package StreamTest;

import java.io.FileInputStream;
//字节读取方式
public class FileInputStreamTest {
    public static void main(String[] args) throws Exception {
        //读取文件创建字节流
        FileInputStream fileInputStream=new FileInputStream("./src/StreamTest/FileInputStreamTest.java");
        //创建读取的通道
        byte[] buff=new byte[1];
        //用来保存已经读取的字节数目
        int readed=0;
        /**
         * 循环读取，读取一次往后移动一次指针，当读取的返回小于0时候，说明已经读取到结尾了
         * 由于数组的长度限制，每次读取的内容不会超过数组长度
         * 每个汉字占据两个字节，有时候由于数组长度的限制，只读到半个，汉字就会显示错误
         */
        while ((readed=fileInputStream.read(buff))>0){
            //将数组中的读取内容取出来
            System.out.println(new String(buff,0,readed));

        }
        //关闭输入流，try catch的时候放在finally里更好
        fileInputStream.close();
    }
}
