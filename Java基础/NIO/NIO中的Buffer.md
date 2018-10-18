### Buffer一般读写走下面的流程:
1. 写入数据到buffer
2. 调用flip方法
3. 从buffer中读取数据
4. 调用clear()方法或者compact()方法

当向buffer写入数据，buffer会记录写了多少数据。调用**filp方法**可以切换**读写模**式，读模式可以读取之前写入到buffer的所有数据。

读完了数据，就要清空缓冲区，然后继续写入。有两种方法:
- **clear()清空整个缓冲区**
- **compact()清除已经读过的数据，没有读取的数据将移动到缓冲区起始处，新写入的数据将追加在后面**

---
### Buffer的capacity,position和limit
缓冲区本质是一块可以读写数据的内存，被包装成了Buffer,然后又一系列的方法用来访问。

buffer有三个属性:
- capacity（读写模式下含义都一样，代表Buffer最大长度，只能往里写capacity个数据，写到buffer满了，必须清空才可以继续写数据）
- position
- limit