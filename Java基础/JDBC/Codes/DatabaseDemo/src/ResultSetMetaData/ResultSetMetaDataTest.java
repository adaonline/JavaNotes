package ResultSetMetaData;

import operationForDB.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//对于结果集的分析
public class ResultSetMetaDataTest {

    public static void action(String sql){
        try {
            Connection conn= DBUtil.getConn();
            Statement statement=conn.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            //获取结果集的ResultSetMetaData

            ResultSetMetaData rsmd=resultSet.getMetaData();
            List<String> columnnames=new ArrayList<>();
            List<List<String>> datas=new ArrayList<List<String>>();

            //ResultSetMetaData可以获取到列数以及列的字段名
            for(int i=0;i<rsmd.getColumnCount();i++){
                columnnames.add(rsmd.getColumnName(i+1));
            }
            /*************获取并输出************/
            while (resultSet.next()){
                List<String> columnsdata=new ArrayList<>();
                for(int i=0;i<rsmd.getColumnCount();i++){
                    columnsdata.add(resultSet.getString(i+1));
                }
                datas.add(columnsdata);
            }
            for(String column:columnnames){
                System.out.print(column+"\t");
            }
            System.out.println("\n");
            for(List<String> da:datas){
                for(String data:da){
                    System.out.print(data+"\t");
                }
                System.out.println("\n");
            }
            DBUtil.close(statement,conn);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        //首先保证存在create_test表(其他程序有创建内容)
        //保证里面有数据
        ResultSetMetaDataTest.action("select * from create_test");
    }
}
