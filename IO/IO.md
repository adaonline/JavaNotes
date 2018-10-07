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

### 3.字节流和字符流

#### 3.1 InputStream/Reader
InputStream/Reader 是所有输入流的抽象基类，他们的方法是所有输入流可以使用的方法。

InputStream API:
- int read():从输入流中读取单个字节，返回读取的字节数据，字节可以转换成int类型
- int read(byte[] b):从输入流中读取b数组长度个字节的数据，并且将其存在数组b中，返回实际读取的字节数目
- int read(byte[] b,int off,int len):从输入流中读取len个字节的数据，并且将其存在数组b中，并不是从数组起点开始存储的，而是从off位置开始，返回实际读取的字节数。

Reader API：同InputStream一样，只不过将字节换成字符。

**当返回的值是-1的时候，说明读取到了输入流的结束点，读取完毕。**

InputStream/Reader是基类，不可以创建实例，可以有自己的实现类，例如FileInputStream和FileReader直接和文件关联。

示例：**IODemo\src\StreamTest\FileInputStreamTest.java和FileReaderTest.java**


#### 3.2 OutputStream/Writer

OutputStream/Writer API(共有的API)：
- void write(int c):将指定的**字节/字符**输出到输出流中，其中c可以代表字节，也可以是字符
- void write(byte[]/char[] buf):将**字节/字符**数组中的数据输出到指定的输出流中
- void write(byte[]/char[] buf,int off,int len)：将**字节/字符**数组中从off位置开始，长度为len的字节/字符输出到输出流中。

**Writer可以用字符串代替字符数组**，所以Writer还可以：
- void writer(String str):将str字符串里包含的字符输出到制定输出流中
- void writer(String str,int off,int len):将str字符串里从off位置开始，长度为len的字符输出到制定输出流中

示例: **IODemo\src\StreamTest\FileOutputStreamTest.java和FileWriterTest.java**

具体一些小内容提示请看程序注释。

### 4.输入输出流的体系结构

之前我们用的都是基类的用法，如果希望简单点，更高效就需要处理流来包装节点流。
#### 4.1处理流，也可以叫包装流

处理流对外提供更加便捷的方法，让开发人员把目标转移到更高级高效的操作。

处理流的构造参数是一个已经存在的流，例如将FileOutputStream包装成PrintStream；而节点流的构造参数则是一个物流IO节点。

处理流，关闭的时候，也会自动关闭自己封装的流。

示例：**IODemo\src\StreamTest\PrintoutStreamTest.java**

#### 4.2输入输出流的体系

输出输出有将近40多个类，之所以这么复杂，是因为把IO流分成了很多类型

- 每个类型里又分别有字节流和字符流，有的类型无法提供字节有的无法提供字符，例如访问字符串就没有字节流读法
- 字符流和字节流里又有输入和输出两个分类
- 一般计算机里的数据都是二进制的，字节流可以处理二进制的文件。但是如果字节流处理文本，就需要用合适办法去把字节转字符，更复杂
- 所以输入输出的是字符串文本内容的时候，用字符流，如果是二进制内容的话，就用字节流。

字符串、数组同样可以作为物理构造节点去构造流。

示例：**IODemo\src\StreamTest\StringAsNodeTest.java**

#### 4.3 转换流、推回输入流、重定向输入输出、读写进程

##### 4.3.1转换流
    
转换流是一种特殊的流，用于将字节流转成字符流，有两种转换流

其中InputStreamReader将字节输出流转成字符输入流，OutputStreamWriter将字节输出流转成字符输出流。

**字符流操作比字节流方便，所以基本不用字符转字节，java语言设计的时候就没有添加字符转字节的转换。**

示例：**IODemo\src\StreamTest\TransFormStream**(键盘输入流不太方便，并且输入的都是字符，就将其转成字符流，将普通的reader包装成BUfferReader，可以用readline读取行)




