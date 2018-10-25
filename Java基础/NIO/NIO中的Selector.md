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
可以将一个对象或者一些信息加在selectionKey上面：
```
selectionKey.attch(object);
Object attchObject=selectionKey.attachment();
```
还可以在用register()方法向Selector注册Channel的时候附加对象：
```
SelectionKey key=channel.register(selector,selectionKey.OP_READ,object);
```




## 4.Selector选择通道
一旦注册了多个通道，就可以调用几个重载的select方法，返回设置的感兴趣的已经准备就绪的通道。

- int select()  **阻塞到至少有一个通道在注册的事件上就绪了**
- int select(long timeout) **设置阻塞的毫秒数**
- int selectNow() **不会阻塞，不管什么就绪就立刻返回，如果没有可选择的通道，返回0**


返回的int值代表有多少通道已经就绪了（返回上次调用select后就绪的通道数目）。如果有一个通道就绪，返回1，如果再次调用，另外一个就绪了，返回1。


#### selectKeys()
有通道就绪后，可以调用selector的这个方法，访问键集：
```
Set selectedKey=selector.selectedKeys();
```
可以遍历这个集合来访问就绪通道：
```
Set selectedKeys = selector.selectedKeys();
Iterator keyIterator = selectedKeys.iterator();
while(keyIterator.hasNext()) {
    SelectionKey key = keyIterator.next();
    if(key.isAcceptable()) {
        //连接就绪
    } else if (key.isConnectable()) {
        //连接就绪
    } else if (key.isReadable()) {
        // 可读就绪
    } else if (key.isWritable()) {
        // 写入就绪
    }
    keyIterator.remove();
}
```
循环遍历每个键，检测事件就绪。

最后需要删除，因为selector不会自己从选择键集中移除selectedKey实例，处理完后必须自己移除。下次就绪会再次放入的。

#### wakeUp()
调用这个方法会让阻塞在select的线程立即返回。

#### close()
调用selector的close会关闭，并且注册的selectedKey实例都会无效，通道本身不会关闭。