package DatagramChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelServerTest
{
    public static void main(String[] args) {
        try {
            DatagramChannel datagramChannel=DatagramChannel.open();
            datagramChannel.socket().bind(new InetSocketAddress(9999));
            ByteBuffer buf = ByteBuffer.allocate(1024);

            while (true){
                buf.clear();
                datagramChannel.receive(buf);
                int position= buf.position();
                buf.flip();
                byte[] b=new byte[1024];
                for(int i=0;i<position;i++){
                    b[i]=buf.get();
                }
                System.out.println(new String(b,"UTF-8"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
