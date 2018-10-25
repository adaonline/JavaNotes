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

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

