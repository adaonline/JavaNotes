package DatagramChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelClientTest {
    public static void main(String[] args) {
        try {
            DatagramChannel channel=DatagramChannel.open();
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            buffer.clear();
            buffer.put("UDP发送信息".getBytes("UTF-8"));
            buffer.flip();
            System.out.print("发送信息");
            int send=channel.send(buffer,new InetSocketAddress("localhost",9999));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
