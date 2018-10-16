package Blob;

import operationForDB.DBUtil;

import java.io.*;
import java.sql.*;

/**
 * 从数据库读取文件二进制数据，然后保存成文件
 */
public class BlobSelectTest {

    public static void select(){
        try {

            Connection conn= DBUtil.getConn();
            PreparedStatement statement=conn.prepareStatement("select imgdata from img where imgname=?");

            statement.setString(1,"java");
            ResultSet resultSet=statement.executeQuery();
            int index=1;
            while (resultSet.next()){
                //结果集从1开始
                Blob blob=resultSet.getBlob("imgdata");
                //生成文件对象
                DataOutputStream outputStream=new DataOutputStream(new FileOutputStream("./src/Blob/outfile/java"+index+".jpg"));
                InputStream inputStream=blob.getBinaryStream();
                byte[] outbyte=new byte[1024];
                int out;
                while ((out=inputStream.read(outbyte))!=-1){
                    outputStream.write(outbyte);
                }
                inputStream.close();
                outputStream.flush();
                outputStream.close();
                index++;
            }

            DBUtil.close(statement,conn);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        BlobSelectTest.select();
    }
}
