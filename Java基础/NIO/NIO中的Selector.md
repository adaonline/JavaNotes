# Selector

Selector可以使用单线程来处理多个Channels，需要更少的线程去处理所有的通道。切换线程需要消耗资源，而且线程也需要消耗资源，所以，线程越少是越有好处的。也不一定必须线程要少，现在cpu在不断发展，多核多线程的性能越来越好，多任务可能性能会更好！！

![image](https://note.youdao.com/yws/api/personal/file/85C3D0F040D0470891131895F3F7A9B8?method=download&shareKey=485272d78c1b9e183ea9bb6c3e79b25c)

## 1. Selector的创建
调用Selector的方法自己创建一个
```
Selector selector=Selector.open();
```
## 2. 向Selector注册通道
Selector管理Channel的第一步，就是需要把Channel注册到Selector上，也是Selector自己的方法：
```
channel.configureBlocking(false);//设置非阻塞模式
SelectionKey key=channel.register(selector,Selectionkey.OP_READ);
```
**++跟Selector一起的channel必须是非阻塞模式的++**，说明阻塞的一些类型Channel就不可以跟Selector一起使用，例如FileChannel，但是一些SocketChannel套接字等等可以。


register方法里的第二个参数，代表监听四种不同类型的事件：
- Connect(成功连接就绪)
- Accept(准备接受就绪)
- Read(读取就绪)
- Write(写入就绪)

通道触发了这个事件，就说明这个事件已经就绪。


四个事件用对应的SelectionKey的四个常量来表示：
- SelectionKey.OP_CONNECT
- SelectionKey.OP_ACCEPT
- SelectionKey.OP_READ
- SelectionKey.OP_WRITE

如果要接触多个时间，可以用“|”操作符连接，如下：
```
int interestSet=SelectionKey.OP_READ|SelectionKey.OP_WRITE;
```

## 3. SelectionKey
当注册Channel的时候，register方法会返回一个SelectionKey对象。这个对象有一些特殊属性：
- interest集合
- ready集合
- Channel
- Selector
- 附加的可选对象

### interest集合属性
interest是SelectionKey所感兴趣的事件集合，可以用SelectionKey读写这个集合：
```
int interestSet=selectionKey.interestOps();
boolean isInterestedInAccept=(interestSet&SelectionKey.OP_ACCEPT)==SelectionKey.OP_ACCEPT;
boolean isInterestedInConnect=interest&SelectionKey.OP_CONNEXT;
boolean isInterestedInRead=interest&SelectionKey.OP_READ;
boolean isInterestedInWrite=interest&SelectionKey.OP_Write;
```
用“位于”操作interest集合和给定的SelectionKey常量，可以确定某个事件是否已经在interest集合中。

### ready集合
ready集合是通道已经准备就绪的集合。
访问方法:
```
int readySet=selectionKey.readyOps();
```
可以用来检测channel中有什么事件或者操作已经就绪。也可以用下面方法，返回Boolean类型：
```
selectionKey.isAcceptAble();
selectionKey.isConnectable();
selectionKey.isReadable();
selectionKey.isWriteable();
```

### Channel+Selector
从selectionKey访问channle还有Selector：
```
Channel channel=selectionKey.Channel();
Selector selector=selectionKey.selector();
```
### 附加对象到selectionKey
可以将一个对象或者一些信息加在selectionKey上面，