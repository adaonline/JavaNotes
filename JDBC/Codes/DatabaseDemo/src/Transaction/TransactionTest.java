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
           int result= statement.executeUpdate(sql);
           System.out.println(sql+" 执行结果:"+result);
        }
        conn.commit();
        DBUtil.close(statement,conn);

    }
    public static void main(String[] args) throws Exception{
        //9999主键不存在所以执行结果成功为1
        //1主键存在，报错，会导致所有本次事务回滚
        String[] sqls=new String[]{
                "insert into create_test values(9999,'tanacs','desc')",
                "insert into create_test values(1,'tanacs','desc')"
        };
        TransactionTest.testaction(sqls);
    }
}
