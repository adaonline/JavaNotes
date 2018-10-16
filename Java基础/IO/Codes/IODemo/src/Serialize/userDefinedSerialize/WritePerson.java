package Serialize.userDefinedSerialize;

import Serialize.SerializeClass.NeedObject;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//序列化某个类到文件
public class WritePerson {
    public static void main(String[] args) {
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("./src/Serialize/userDefinedSerialize/[自定义序列化]测试文件.txt"));
            Person person=new Person("person",1);

            objectOutputStream.writeObject(person);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
