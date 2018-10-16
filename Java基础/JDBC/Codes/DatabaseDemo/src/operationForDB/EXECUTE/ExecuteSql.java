package operationForDB.EXECUTE;

import operationForDB.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class ExecuteSql {
    public void executeSql(String sql){
        try {
            Connection conn= DBUtil.getConn();
            Statement statement=conn.createStatement();
            boolean result= statement.execute(sql);
            //execute执行sql的时候，如果有结果集返回true，没有结果则为false
            if(result){
                ResultSet rs=statement.getResultSet();
                //ResultSetMetaData用于分析结果集合的元数据接口
                ResultSetMetaData resultSetMetaData=rs.getMetaData();
                //获取列数
                int columeNum=resultSetMetaData.getColumnCount();
                while (rs.next()){
                    for(int i=0;i<columeNum;i++){
                        System.out.print(rs.getString(i+1)+"\t");
                    }
                    System.out.println("\n");
                }

            }else{
                System.out.println("执行没有结果，影响数据行有"+statement.getUpdateCount()+"条");
            }
            DBUtil.close(statement,conn);
        }catch (Exception e){
            /**
             * Establishing SSL connection without server's identity verification is not recommended
             * 出现的异常错误（是Mysql数据库的SSL连接问题，提示警告不建议使用没有带服务器身份验证的SSL连接）
             * 在数据库连接的url中添加useSSL=false
             */
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        ExecuteSql es=new ExecuteSql();
        System.out.println("#####创建表语句execute执行#####");
        String sql="create table create_test" +"(id int auto_increment primary key," +"testname varchar(255)," +"testdesc text);";
        es.executeSql(sql);
        System.out.println("#####插入表语句execute执行#####");
        for(int i=0;i<10;i++){
            String insertsql="insert into create_test(testname,testdesc) values('name"+i+"','desc"+i+"');";
            es.executeSql(insertsql);
        }

        System.out.println("#####查询表语句execute执行#####");
        String selectsql="select * from create_test";
        es.executeSql(selectsql);
        System.out.println("#####删除表记录语句execute执行#####");
        String deletesql="delete from create_test where testname='name1';";
        es.executeSql(deletesql);
        System.out.println("#####删除表语句execute执行#####");
        String dropsql="drop table create_test";
        es.executeSql(dropsql);


    }
}
