package Transaction;

import operationForDB.DBUtil;

import java.sql.Connection;
import java.sql.Statement;

public class TransactionTest {
    public static void testaction(String[] sqls) throws Exception{

        Connection conn= DBUtil.getConn();
        Statement statement=conn.createStatement();
        //关闭自动提交，从而开启事务
        conn.setAutoCommit(false);
        for(String sql:sqls){
            statement.executeUpdate(sql);
        }
        conn.commit();
        DBUtil.close(statement,conn);

    }
    public static void main(String[] args) throws Exception{

    }
}
