# JDBC典型用法
## 一. 常用接口API
###### ++DriverManager:用于管理JDBC驱动的服务类。主要用来获取Connection对象++
- public static Connection getConnection(String url,String user, String password) throws SQLException --用来获取对应数据库的连接
###### Connection: 数据库连接对象，每一个Connection代表一个物理连接回话，访问数据库是必须获得的。
- Statement createStatement() throws SQLException;
-  PreparedStatement prepareStatement(String sql) throws SQLException; --返回预编译的Statement对象
-  CallableStatement prepareCall(String sql) throws SQLException; --返回CallableStatement对象，用于存储过程。

###### ++Statement：用于执行SQL查询。可以用于执行DDl，DCl,DMl,也可以用来SQL查询。sql查询时候返回的是结果集++
- ResultSet executeQuery(String sql) throws SQLException;--用于查询语句，用于返回结果集，只能用于查询
- int executeUpdate(String sql) throws SQLException; --用于执行DML语句，返回影响的行数，也可以DDL语句，返回0
- boolean execute(String sql) throws SQLException; --++用于执行==任何sql语句==，如果执行结果有结果集，则返回true；如果执行后第一个结果为受影响行数或者没有结果则返回false++。

###### ++PreparedStatement:预编译的Statement对象。可以预编译SQl语句（带有参数那种），后面只需要改参数即可，避免每次都去编译SQL语句，性能更好，每次执行只需要传入参数即可。同样有Statement默认的三个方法，但是不用接受SQL字符串了，已经编译好了，只需要传入参数即可。还有个很好的作用，防止SQL注入。++

- void setXxx(int parameterIndex,Xxx value)：不同的传入参数，用不同的方法。前一个参数是指定位置的参数
- 

###### ++CallableStatement存储过程专用++：
继承于PreparedStatement，用于执行存储过程的接口，具体内容看后续示例。

###### ++ResultSet：结果集对象++
- close():释放结果集
- boolean first():将结果集的记录指针定位到上一行。如果指针指向的记录有效，则返回true。
- void beforeFirst():将指针定位到首行之前，也就是初始状态。
- boolean previous():将指针定位到上一行，如果记录有效，则返回true
- boolean next(): 将指移动到下一行，如果记录有效则返回true
- boolean last(): 将指针移动到最后一行
- 指针到了指定行时候，可以通过getXxx(int columnindex) 或者 getXxx(String columnlabel)方法获取当前行的指定列的值，前一个根据索引，后者根据列名获取。还有一个getObject或者对应列的对象


## 二. JDBC步骤
1. 反射加载驱动
2. 获取数据库连接
3. 执行sql语句
4. 操作结果集


```
package connectMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectMysqlDemo {
    public static void main(String[] args) throws Exception{
        //1.反射加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        try{
            //2.获取数据库连接
            Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database","root","password");
            //3.创建statement对象
            Statement stmt=conn.createStatement();

            //执行sql语句
            /**
             * 1.execute()可以执行任何sql返回一个boolean值(如果第一个结果是relultset则返回true,如果是一个update，或者没有结果则返回false)
             * 2.executeQuery()执行select语句，返回查询到的结果集
             * 3.executeUpdate()用于执行DML语句，返回一个整数，代表被这个语句影响的记录数目
             */
            ResultSet rs=stmt.executeQuery("SELECT * from pet");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getInt(2)+"\t"+rs.getString(3));
            }
        }catch (Exception e) {
            System.exit(0);
        }


    }
}

```

## 三.基础示例解析
###### 1.statement
有三种方法执行SQL，之前的程序中使用了executeQuery()执行查询。
可以使用execute()来执行DDL语句还有DML语句。区别是DDL语句返回为0，DML返回受到影响的记录数。
++Java8增加了多个重载的executeLargeUpdate()方法，返回值为long类型，意思就是返回记录条数超过了Integer的最大值，可以用这个方法。++

示例代码:DatabaseDemo\src\operationForDB

其中有DDL执行办法，DML执行办法，execute()方法示例

###### 2.PreparedStatement
是statement接口的子接口，可以预编译SQL语句，==预编译的sql语句被存储在PreparedStatement对象中==，效率更高。无须拼接SQL语句，预防sql注入。

PreparedStatement也有三大方法执行sql，只不过不用传参数，因为已经预编译了sql语句。

示例代码：DatabaseDemo\src\StatementTest\PreparedStatement

###### 3.CallableStatement
通过Connection的preapreCall()获取该对象，调用存储过程格式：{call 过程名(?,?,?....)}，问号为占位符。

示例代码：DatabaseDemo\src\StatementTest\CallableStatement

## 四.处理大对象，例如Blob
Blob是二进制长对象的意思，通常用来存储大文件，一般为图片或者声音。在数据库里，用Blob类型来保存该数据。</br>

Blob用常量是无法表示的，所以数据库操作需要用到PreparedStatement来操作，其中用到的方法：

void setBinaryStream(int parameterIndex, java.io.InputStream x,int length) throws SQLException;
用于将二进制流写入参数。

取出blob数据的时候，可以用ResultSet里的getBlob方法(可以传列下标或者字段名)，可以获得对应的Blob，blob里又有
方法可以获取输入流，用来操作：

java.io.InputStream getBinaryStream () throws SQLException;

示例代码：DatabaseDemo\src\Blob (里面有插入，还有读取保存操作)

## 五.ResultSetMetaData分析结果集合
执行SQL后得到的结果集合ResultSet可以用记录指针来遍历操作，如果不清楚结果集合里的内容，可以用ResultSetMetaData来获取对应信息。
ResultSet里有个getMetaData()方法用来获取ResultSetMetaData。

ResultSetMetaData常用方法：

- int getColumnCount() throws SQLException;--获取该结果集的列数量
- String getColumnName(int column) throws SQLException;--获取对应索引的列名
- int getColumnType(int column) throws SQLException; 返回对应的列类型，不同数字代表不同类型


示例代码：DatabaseDemo\src\ResultSetMetaData

## 六.事务处理

JDBC提供了事务支持，由Connnection提供，Connnection默认是自动提交的，也就是默认关闭事务，这样每次执行sql语句，就会立刻提交到数据库，直接生效无法回滚。

可以调用Connnection的setAutoCommit()方法来关闭自动提交：
```
//关闭自动提交，开启事务
setAutoCommit(false);
```
还可以用getAutoCommit()方法来获取当前连接的自动提交模式是什么。

事务开始后，可以完全按照之前的方式操作数据库，但是SQl所做的修改并不会立刻生效，因为事务没有提交，可以采用commit()方法来提交事务：
```
//提交事务
conn.commit();
```
可以采用rollback()方法来回滚事务：
```
conn.rollback();
```

当connection遇到一个SQlException异常的时候，系统不会正常退出，事务会回滚的。但是如果捕获了异常，则需要在catch中显式的回滚事务才可以。

Connection也有中间点的方法，也就是回滚到某个点的方式：
```
//创建一个未命名的中间点，返回中间点对象
Savepoint setSavePoint();
//创建指定名称的中间点，返回对象
Savepoint setSavepoint(String name);
//回滚到指定的中间点
rollback(Savepoint savepoint);
```
还有对应的批量更新的内容，具体在示例中说明。
示例：DatabaseDemo\src\Transaction

## 七.数据库连接池
每一次操作一次数据库就要重新新建一次连接，然后操作完后重新关闭，这样导致系统性能低下，效率下降。
解决方案：应用启动时候，创建一定数目的连接组成连接池，在需要的时候从中获取连接，操作完放回，无须关闭或者重新打开，这样将大大提高运行效率。

JDBC数据库连接池由javax.sql.DataSouece接口表示，实现有DBCP,C3P0等等。