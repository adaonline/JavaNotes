package SocketChannel;


import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
//传统的server写法
public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket=null;
        InputStream in=null;
        try {
            serverSocket=new ServerSocket(9999);
            byte[] receivebuff=new byte[1024];
            int getsize=0;

            while (true){
                Socket clientsocket=serverSocket.accept();
                SocketAddress address=clientsocket.getRemoteSocketAddress();
                System.out.println("接受到连接来自"+address);
                in=clientsocket.getInputStream();
                while ((getsize=in.read(receivebuff))!=-1){
                    byte[] temp=new byte[getsize];
                    System.arraycopy(receivebuff,0,temp,0,getsize);
                    System.out.println(new String(temp));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(serverSocket!=null){
                    serverSocket.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
