package StreamTest.RandomAccessFile;


import java.io.RandomAccessFile;
//RandomAccessFile 移动指针读取文件
public class RandomAccessFileTest {
    public static void main(String[] args) {
        try {
            //只读方式打开文件
            RandomAccessFile randomAccessFile=new RandomAccessFile("./src/StreamTest/RandomAccessFile/RandomAccessFileTest.java","r");
            //初始位置一开始是0
            System.out.println("指针的初始位置："+randomAccessFile.getFilePointer());
            //移动指针400字节
            randomAccessFile.seek(400);
            byte[] buf=new byte[1024];

            int readed=0;
            //读取就像普通读取一样，指针自动向后移动
            while ((readed=randomAccessFile.read(buf))>0){
                System.out.println(new String(buf,0,readed));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
