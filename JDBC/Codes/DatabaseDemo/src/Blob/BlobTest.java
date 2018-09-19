package Blob;

import operationForDB.DBUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class BlobTest {

    public static void insert(String file){
        try {
            String name=file.substring(file.lastIndexOf("\\")+1,file.lastIndexOf("."));
            Connection conn= DBUtil.getConn();
            PreparedStatement statement=conn.prepareStatement("insert into img value(1,?,?)", Statement.RETURN_GENERATED_KEYS);
            File f=new File(file);
            InputStream ins=new FileInputStream(f);
            statement.setString(1,name);
            statement.setBinaryStream(2,ins,(int)f.length());
            int updateresult=statement.executeUpdate();
            DBUtil.close(statement,conn);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String filename="./src/Blob/java.jpg";
        BlobTest.insert(filename);
    }
}
