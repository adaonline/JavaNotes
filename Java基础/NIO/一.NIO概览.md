# NIO
## 概览
NIO三大核心部分：
- Channel(通道)
- Buffer(缓冲区)
- Selector(选择器)


#### 1. 基于的操作对象

传统的IO基于字节流和字符流，而NIO基于Channel和Buffer进行操作，数据总是从通道读取到缓冲区，从缓冲区写入到通道。Selector用于监听多个通道的事件(例如：连接的开启和关闭，数据的到达还有分配等等)。

#### 2. 读取写入方式
传统IO是面向流的，NIO是面向缓冲区的，传统IO是读取的时候从流中读取，指针自动后移，一直到读完，指针不能随便移动，移动的话需要有一定的缓冲区去存放(传统IO的推回流，还有RandomAcessFile可以有一定的能力去随机读取，但是实用价值不大)。

NIO不同，数据读取到一个缓冲区里，可以在缓冲区里前后移动，灵活性更大，但是有了缓冲区，数据被新读取的覆盖问题还有验证就更需要严格谨慎。

#### 3.关于线程阻塞方面的影响
传统IO是阻塞的，会一直等到线程读完，或者写入完全，这个线程会一直阻塞。NIO不同，是一种非阻塞模式，是一个线程发送请求读取的同时，又能得到目前可以读的数据；当数据读取就绪之前，线程完全可以去做其他事情。写入也是一样，在请求写入数据到通道的时候，可以不用等待完全写入，这个线程可以做其他事情。最终，非阻塞IO的空闲时间，可以用在其他的IO，这样一个单独的线程，就可以管理多个通道去输入和输出了。


---
### Channle

基本就是通道的意思，跟IO中的Stream差不多。但是stream是单向的，只能读或者只能写，Channel是双向的，可以读也可以写。

NIO中Channel的实现有：
- FileChannel (对应文件IO)
- DatagramChannel (对应UDP)
- SocketChannel (对应TCP的Client)
- ServerSocketChannel (对应TCP的Server)

### Buffer
实现的Buffer有：
ByteBuffer,CharBuffer,DoubleBuffer,FloatBuffer,IntBUffer,LongBuffer,ShortBuffer，对应的基本数据类型有：byte,char,double,float,int,long,short。还有一些比较高级的，MappedByteBuffer,HeapByteBuffer,DirectByteBuffer等，后续会介绍

### Selector

Selector可以实现单线程处理多个Channel，如果每个通道的流量都不大，使用Selector是很好的选择。使用一个Selector，首先要将Channel注册到Selector，然后调用select()方法，这个方法会阻塞到有某个通道的事件就绪，返回后，这个阻塞的线程就可以处理这些事件了!



**Buffer和Channel的示例:NIODemo\src\Channel(其中描述了一个很简单的NIO读取文件的操作，用到了Channel和Buff)**