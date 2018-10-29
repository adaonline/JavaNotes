package MappedByteBuffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {
    public static void main(String[] args) {
        mappedFile();
        method();
    }
    public static void mappedFile(){
        RandomAccessFile accessFile=null;
        FileChannel fileChannel=null;
        try {
            accessFile=new RandomAccessFile("src/JavaScript.pdf","rw");
            fileChannel=accessFile.getChannel();
            long start=System.currentTimeMillis();
            MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_ONLY,0,accessFile.length());
            long end=System.currentTimeMillis();
            System.out.println("MappedByteBuffer cost time:"+(end-start));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(accessFile!=null){
                    accessFile.close();
                }
                if(fileChannel!=null){
                    fileChannel.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void method(){
        RandomAccessFile accessFile=null;
        FileChannel fileChannel=null;
        try {
            accessFile=new RandomAccessFile("src/JavaScript.pdf","rw");
            fileChannel=accessFile.getChannel();
            long start=System.currentTimeMillis();
            ByteBuffer buff=ByteBuffer.allocate((int)accessFile.length());
            buff.clear();
            fileChannel.read(buff);
            long end=System.currentTimeMillis();
            System.out.println("read cost time:"+(end-start));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(accessFile!=null){
                    accessFile.close();
                }
                if(fileChannel!=null){
                    fileChannel.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
