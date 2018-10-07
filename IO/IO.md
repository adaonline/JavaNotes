# 一.File
File提供了几大类去访问和操作目录：(大概提一下)
1. 访问文件名相关方法
2. 文件检测相关
3. 获取常规信息
4. 文件操作
5. 目录操作

示例操作：IODemo\src\FileTest

# 二. IO流
### 1.流的分类
#### 1.1输入输出流

按照流向来分，可以分为输入输出流。输入流采用InputStream还有Reader，输出流则用OutputStream和Writer作为基类，都是抽象类，无法实例化的，有具体的子类去操作。

**输入流：只能从中读取数据，而不能向其写数据。**

**输出流：只能向其中写入数据，而不能读取数据。**

举例：(通过数据流向判断输出输入流)数据从server端流向客户端，在server端锁采用的就应该是输出流，在client端就应该采用的是输入端。

#### 1.2字节流和字符流

用法完全一样，只不过操作的数据单元不一样，字节流操作是8位的字节，字符流是16位的字符。
字节流主要用InputStream还有OutputStream作为基类，而字符流则主要用Reader还有Writer作为基类。

#### 1.3节点流和处理流
按照流的角色来区分，分为节点流处理流。
向一个特定的数据源读写数据的流，称为节点流，也叫低级流，使用节点流的时候，是直接连接数据源和程序的。处理流则对一个存在的流进行连接或者封装，通过封装的流实现读写，也称为高级流。处理流有个好处，就是相同的处理流，可以访问不同的数据源。

### 2.流的模型
JAVA的IO流40多个类基本全部由下列派生的：
- InputStream/Reader：所有输入流的基类，前一个是字节输入流，后面是字符输入流;
- OutputStream/Writer：所有输出流的基类，前一个是字节输出流，后一个是字符输出流.


对于输入流来说，可以想象成一个管道：

![image](https://note.youdao.com/yws/api/personal/file/FF79E7D59D4D4171819974BC20AEEFAE?method=download&shareKey=ddf7b63e40ed55c672733ba4a06c4c35)

输入流用一个指针来表示读取的位置，每当程序从InputStream/Reader读取一个内容后，指针自动往后移动，自动哦!并且，InputStream/Reader里也提供了一些指针API.


对于输出流来说，也是一个管道，只不过这个管道一开始是空的：

![image](https://note.youdao.com/yws/api/personal/file/FF79E7D59D4D4171819974BC20AEEFAE?method=download&shareKey=ddf7b63e40ed55c672733ba4a06c4c35)