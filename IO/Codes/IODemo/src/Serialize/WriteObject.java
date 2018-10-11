package Serialize;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class WriteObject {
    public static void main(String[] args) {
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("序列化测试文件.txt"));
            NeedObject needObject=new NeedObject(1,"one");

            objectOutputStream.writeObject(needObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
