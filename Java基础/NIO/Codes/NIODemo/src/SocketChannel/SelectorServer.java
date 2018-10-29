package SocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

public class SelectorServer {
    private static final int PORT=9999;
    public static void main(String[] args) {
        selector();
    }

    public static void selector(){
        Selector selector=null;
        ServerSocketChannel serverSocketChannel=null;
        try {
            selector=Selector.open();
            serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true){
                if(selector.select(2000)==0){
                    System.out.println("---");
                    continue;
                }
                Iterator<SelectionKey> iter=selector.selectedKeys().iterator();
                while (iter.hasNext()){
                    SelectionKey key=iter.next();
                    if(key.isAcceptable()){
                        accept(key);
                    }
                    if(key.isReadable()){
                        read(key);
                    }
                    if(key.isWritable()&&key.isValid()){
                        write(key);
                    }
                    if(key.isConnectable()){
                        System.out.println("connection ready");
                    }
                    iter.remove();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(selector!=null){
                    selector.close();
                }
                if(serverSocketChannel!=null){
                    serverSocketChannel.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public static void accept(SelectionKey key) throws IOException{
        ServerSocketChannel serverSocketChannel=(ServerSocketChannel)key.channel();
        SocketChannel socketChannel=serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocateDirect(1024));
    }
    public static void read(SelectionKey key) throws IOException{
        SocketChannel channel=(SocketChannel)key.channel();
        ByteBuffer buffer=(ByteBuffer)key.attachment();
        long read=channel.read(buffer);

        /**
         * Java.nio.charset.Charset处理了字符转换问题
         * 通过构造CharsetDecoder还有CharsetEncoder用来转换字符和字节
         */
        Charset charset=Charset.forName("UTF-8");
        CharsetDecoder decoder=charset.newDecoder();
        CharBuffer charBuffer=CharBuffer.allocate(1024);

        while (read>0){
            buffer.flip();
            decoder.decode(buffer,charBuffer,false);
            charBuffer.flip();
            while (charBuffer.hasRemaining()){
                System.out.print(charBuffer.get());
            }

            System.out.println();
            charBuffer.clear();

            buffer.clear();
            read=channel.read(buffer);
        }
        if(read==-1){
            channel.close();
        }
    }

    public static void write(SelectionKey key)throws IOException{
        ByteBuffer buffer=(ByteBuffer)key.attachment();
        buffer.flip();
        SocketChannel socketChannel=(SocketChannel) key.channel();
        while (buffer.hasRemaining()){
            socketChannel.write(buffer);
        }
        buffer.compact();
    }
}
