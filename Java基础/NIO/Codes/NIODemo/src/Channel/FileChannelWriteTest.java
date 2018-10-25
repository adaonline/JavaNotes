package Channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelWriteTest {
    public static void main(String[] args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile("./src/Channel/channelWrite测试文件.txt", "rw");
            FileChannel inChannel = aFile.getChannel();
            String newData = "channel 写入内容";

            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());

            buf.flip();

            while(buf.hasRemaining()) {
                inChannel.write(buf);
            }
            inChannel.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
