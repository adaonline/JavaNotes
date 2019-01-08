package db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import exception.DBException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    private static DataSource dataSource=null;
    static {
        //数据库连接池
        dataSource=new ComboPooledDataSource("javawebapp");
    }

    /**
     * 获取数据库连接池
     * @return
     */
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
            throw new DBException("数据库异常");
        }
    }

    /**
     * 关闭数据库连接
     * @param connection
     */
    public static void release(Connection connection){
        try {
            if(connection!=null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new DBException("数据库异常");
        }
    }

    /**
     * 关闭数据库连接
     * @param resultset
     * @param statement
     */
    public static void release(ResultSet resultset, Statement statement){
        try {
            if(resultset!=null){
                resultset.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new DBException("数据库异常");
        }
        try {
            if(statement!=null){
                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new DBException("数据库异常");
        }

    }

}
