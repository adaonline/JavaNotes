package ConnectionPools.DBCP;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
//需要去DBCP网站下载对应jar包，或者maven导入
public class DBCPTest {

    public static void action(){
        //数据源对象
        BasicDataSource source=new BasicDataSource();
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://127.0.0.1:3306/database?useSSL=false");
        source.setUsername("root");
        source.setPassword("password");
        source.setInitialSize(5);//初始连接数目
        source.setMaxTotal(20);//最大连接数目
        source.setMinIdle(2);//最小连接数目
        try {
            Connection conn=source.getConnection();//从连接池得到连接进行操作
            //do something

            conn.close();//这里并没有断开到数据库的物理连接，只是将连接归还到连接池
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
