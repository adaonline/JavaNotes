package transferFrom_transferTo;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferToTest {
    public static void main(String[] args) {
        RandomAccessFile from=null;
        RandomAccessFile to=null;
        try {
            from=new RandomAccessFile("src/transferFrom_transferTo/from.txt","rw");
            FileChannel fromchannel=from.getChannel();
            to=new RandomAccessFile("src/transferFrom_transferTo/to.txt","rw");
            FileChannel tochannel=to.getChannel();

            long position=0;//从0位置开始向目标文件写入，count代表最多传输的字节
            long count=fromchannel.size();
            System.out.println(count);
            fromchannel.transferTo(position,count,tochannel);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(from!=null){
                    from.close();
                }
                if(to!=null){
                    to.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
