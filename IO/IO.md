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
- 1.1输入输出流

按照流向来分，可以分为输入输出流。输入流采用InputStream还有Reader，输出流则用OutputStream和Writer作为基类。

- 1.2字节流和字符流

用法完全一样，只不过操作的数据单元不一样，字节流操作是8位的字节，字符流是16位。

- 1.3节点流和处理流

JAVA的IO流基本全部由下列派生的：
InputStream/Reader;OutputStream/Writer.