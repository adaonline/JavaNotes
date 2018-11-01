package Scatter_And_Gatter;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterGatterTest {
    public static void main(String[] args) {
        ByteBuffer one=ByteBuffer.allocate(1024);
        ByteBuffer two=ByteBuffer.allocate(1024);
//        byte[] b1={'0','1'};
//        byte[] b2={'2','3'};
        one.put("这是第一行".getBytes());
        two.put("这是第二行".getBytes());
//        one.put(b1);
//        two.put(b2);
        one.flip();
        two.flip();
        ByteBuffer[] buffs={one,two};

        try {
            FileOutputStream outputStream=new FileOutputStream("src/Scatter_And_Gatter/out.txt");
            FileChannel channel=outputStream.getChannel();
            channel.write(buffs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
