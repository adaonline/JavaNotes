package StreamTest.RandomAccessFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile的中途写入
 * 直接移动指针到中间写入会覆盖后续内容的
 * 如果需要向指定的位置插入内容，
 * 1.程序需要把插入点后面的内容读入缓冲区，
 * 2.插入需要写入的内容后，
 * 3.再把缓冲区的文件追加到后面
 */
public class RandomAccessMiddleAddTest {
    public static void main(String[] args) {
        try {
            //创建临时文件
            File file=File.createTempFile("temp",null);
            //退出时候删除临时文件
            file.deleteOnExit();

            RandomAccessFile randomAccessFile=new RandomAccessFile("./src/StreamTest/RandomAccessFile/RandomAccessFile插入写入测试文件.txt","rw");
            //使用临时文件保存数据
            FileOutputStream outputStream=new FileOutputStream(file);
            FileInputStream inputStream=new FileInputStream(file);
            //移动指针到指定位置
            randomAccessFile.seek(100);
            byte[] buf=new byte[1024];
            //已经读取的字数
            int readed=0;
            //将指针后续的内容写入临时文件
            while ((readed=randomAccessFile.read(buf))>0){
                outputStream.write(buf,0,readed);
            }

            //后续就是开始插入要写的内容了
            //因为已经写入了，所以指针后移了，重新定位一下
            randomAccessFile.seek(100);
            randomAccessFile.write("[这是插入的内容]".getBytes());

            //从临时文件里读出来缓存的内容，加上去
            while ((readed=inputStream.read(buf))>0){
                randomAccessFile.write(buf,0, readed);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
