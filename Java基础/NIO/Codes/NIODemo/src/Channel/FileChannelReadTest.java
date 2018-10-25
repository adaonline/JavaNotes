package Channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelReadTest {
    public static void main(String[] args) {
        RandomAccessFile accessFile=null;
        try {

            accessFile=new RandomAccessFile("./src/Channel/channelRead测试文件.txt","rw");
            FileChannel channel=accessFile.getChannel();
            /**
             * 当然也可以通过FileInputStream.getChannel()进行操作
             */
//          FileInputStream fileInputStream=new FileInputStream("./src/Channel/channel测试文件.txt");
//          FileChannel channel=fileInputStream.getChannel();

            /**
             * Java.nio.charset.Charset处理了字符转换问题
             * 通过构造CharsetDecoder还有CharsetEncoder用来转换字符和字节
             */
            Charset charset=Charset.forName("UTF-8");
            CharsetDecoder decoder=charset.newDecoder();

            ByteBuffer buf=ByteBuffer.allocate(1024);
            CharBuffer charBuffer=CharBuffer.allocate(1024);

            int readnum=channel.read(buf);
            System.out.println(readnum);
            while (readnum!=-1){
                buf.flip();
                decoder.decode(buf,charBuffer,false);
                charBuffer.flip();
                while (charBuffer.hasRemaining()){
                    System.out.print(charBuffer.get());
                }
                charBuffer.clear();
                buf.compact();//compact会继续接着上次没有读完的位置
                readnum=channel.read(buf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(accessFile!=null){
                    accessFile.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }
}
