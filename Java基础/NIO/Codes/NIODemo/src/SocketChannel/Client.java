package SocketChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        SocketChannel socketChannel=null;
        try{
            socketChannel=SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));
            if(socketChannel.finishConnect()){
                int i=0;
                while (true){
                    Thread.sleep(1000);
                    String info="这是来自客户端的第"+i+++"次!";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while(buffer.hasRemaining()){
                        System.out.println("客服端发送："+buffer);
                        socketChannel.write(buffer);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(socketChannel!=null){
                    socketChannel.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

