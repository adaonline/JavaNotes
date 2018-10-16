package ConnectionPools.C3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class C3P0Test {
    public static void acition(){
        try {
            ComboPooledDataSource source=new ComboPooledDataSource();
            source.setDriverClass("com.mysql.jdbc.Driver");
            source.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/database?useSSL=false");
            source.setUser("root");
            source.setPassword("password");
            source.setMaxPoolSize(40);//最大连接数
            source.setMinPoolSize(2);//最小连接数
            source.setInitialPoolSize(10);//初始连接数
            source.setMaxStatements(200);//缓存statement的最大数目

            Connection conn=source.getConnection();//操作同样可以从线程池获取连接，
            conn.close();//关闭的时候归还连接池
        }catch (Exception e){

        }

    }
}
