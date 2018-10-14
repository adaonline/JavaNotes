package StreamTest.RandomAccessFile;

import java.io.RandomAccessFile;

//RandomAccessFile在文件后追加内容
public class RandomAccessAppendText {
    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccessFile=new RandomAccessFile("./src/StreamTest/RandomAccessFile/[RandomAccessFile追加]测试文件.txt","rw");
            //直接将指针移动到最后
            randomAccessFile.seek(randomAccessFile.length());
            //每运行一次就给后面增加一次内容
            randomAccessFile.write("RandomAccessFile追加内容\n".getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
