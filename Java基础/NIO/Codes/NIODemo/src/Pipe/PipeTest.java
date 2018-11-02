package Pipe;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PipeTest {
    public static void main(String[] args) {
        Pipe pipe=null;
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        try {
            pipe=Pipe.open();
            final Pipe pipetmp=pipe;
            executorService.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    //向通道中写入数据
                    Pipe.SinkChannel sinkChannel=pipetmp.sink();
                    int i=0;
                    while(true){
                        Thread.sleep(1000);
                        String data="data from sink!"+i;
                        ByteBuffer buf=ByteBuffer.allocate(1024);
                        buf.clear();
                        buf.put(data.getBytes());
                        buf.flip();
                        while (buf.hasRemaining()){
                            sinkChannel.write(buf);
                        }
                        i++;
                    }
                }
            });
            executorService.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    //从通道读取
                    Pipe.SourceChannel sourceChannel=pipetmp.source();
                    while (true){
                        ByteBuffer buf=ByteBuffer.allocate(1024);
                        buf.clear();
                        int byteReads=sourceChannel.read(buf);
                        System.out.println("读取长度"+byteReads);
                        while (byteReads>0){
                            buf.flip();
                            byte[] b=new byte[byteReads];
                            int i=0;
                            while (buf.hasRemaining()){
                                b[i]=buf.get();
                                i++;
                            }
                            String s=new String(b);
                            System.out.println("读取内容："+s);
                            byteReads=sourceChannel.read(buf);
                        }

                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }

    }
}
