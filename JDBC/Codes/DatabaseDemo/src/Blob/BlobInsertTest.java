package Blob;

import operationForDB.DBUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 */
public class BlobInsertTest {

    public static void insert(String file){
        try {

            Connection conn= DBUtil.getConn();
            PreparedStatement statement=conn.prepareStatement("insert into img value(null,?,?)");
            File f=new File(file);
            InputStream ins=new FileInputStream(f);
            statement.setString(1,"java");
            statement.setBinaryStream(2,ins,(int)f.length());
            int updateresult=statement.executeUpdate();
            System.out.println("结果"+updateresult);
            DBUtil.close(statement,conn);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String filename="./src/Blob/java.jpg";
        BlobInsertTest.insert(filename);
    }
}
