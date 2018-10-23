### 一.Buffer一般读写走下面的流程:
1. 分配空间()
 ```
 ByteBuffer buf=ByteBuffer.allocate(1024);
 还有一种事allocateDirector
 ```
2. 写入数据到buffer
```
int bytesRead=fileChannel.read(buf);
```
2. 调用flip方法切换读写模式
```
buf.flip()
```
3. 从buffer中读取数据
```
buf.get()
```
4. 调用clear()方法或者compact()方法

当向buffer写入数据，buffer会记录写了多少数据。调用**filp方法**可以切换**读写模**式，读模式可以读取之前写入到buffer的所有数据。

读完了数据，就要清空缓冲区，然后继续写入。有两种方法:
- **clear()清空整个缓冲区**
- **compact()清除已经读过的数据，没有读取的数据将移动到缓冲区起始处，新写入的数据将追加在后面**

---
### 二.Buffer的capacity,position和limit
缓冲区本质是一块可以读写数据的内存，被包装成了Buffer,然后又一系列的方法用来访问。

buffer有三个属性:
- **capacity**

（读写模式下含义都一样，代表Buffer最大长度，只能往里写capacity个数据，写到buffer满了，必须清空才可以继续写数据）
- **position**

(写数据到buffer中的时候，position代表当前的位置，初始的时候为0，当写入一个byte、int等类型的时候，position会往后移动到下一个可以写入数据的单元，position最大为capacity-1，就像数组一样;读取数据的时候，也是从某个特定的位置开始，buffer切换到读模式，position位置会变成0，然后开始读取，读取一次，position会移动到下一个可读位置)
- **limit**

(写模式下，Buffer的limit代表可以最多写入多少数据，也就是在写模式时候，limit等于capacity。
读模式时候，limit代表能读到多少数据。所以，当我们切换到读模式的时候，limit会被设置成position的值，就能读到之前写入的所有数据了。)

![image](https://note.youdao.com/yws/api/personal/file/5DB0242D5A2A4E5987BBBF0212403FC5?method=download&shareKey=597432067a811d5b86dd531d0f6ec26a)



### 三.操作详细讲解

**分配**

要获得一个buffer就要先分配。采用allocate方法
```
ByteBuffer buf=ByteBuffer.allocate(1024);
CharBuffer buf=CharBuffer.allocate(1024);
```

**写数据到Buffer里**

写数据有两种方法
- 从channel写到buffer
- 用buffer的put方法写进去
```
int bytesRead=inChannel.read(buf);
but.put(12);//put还有很多其他内容，写到指定位置，或者覆盖等等。
```
**读取数据**
 
- 从Buffer读取数据到Channel
- 使用get从buffer中读取数据
```
int bytewrite=inchannel.write(buf);
byte b=buf.get();
```

**flip()方法**

flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。

**rewind()方法**

Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）

**clear()与compact()方法** 

读完数据准备再次写入的时候，可以调用clear()与compact()方法。
clear方法的话，position将被设为0，limit将被设置成capacity，也就是说看起来buffer被清空了，其实并没有清除数据，但是新的写入标志已经在开头了。数据已经已经被遗忘掉了。

如果还有一些数据未读，还有数据需要写入的话，就要用compact()方法。compact()会将所有没有读取的数据拷贝到buffer开始的地方，然后，把position设置到最后的一个未读元素的最后，limit在capacity的地方，这样，position与limit的中间就是可以写入未读的区间，跟之前的未读可以连接起来。

**equals()与compareTo()方法**
(注：剩余元素是从position到limit之间的元素)

equals()满足以下，代表两个buffer相等。
- 相同的数据类型
- 剩余的数据类型元素数目相等
- 剩余的所有元素相等

compareTo()方法，用来比较两个buffer的剩余元素。如果满足:
- 第一个不相等的元素小于另一个buffer中对应的元素
- 所有元素都相等，但是第一个buffer元素数目比另外一个少。

则认为是小于另一个buffer