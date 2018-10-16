package Serialize.SerializeClass;

import Serialize.SerializeClass.NeedObject;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
//序列化某个类到文件
public class WriteObject {
    public static void main(String[] args) {
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("./src/Serialize/SerializeClass/[序列化]测试文件.txt"));
            NeedObject needObject=new NeedObject(1,"one");

            objectOutputStream.writeObject(needObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
