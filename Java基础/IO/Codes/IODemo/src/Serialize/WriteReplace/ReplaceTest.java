package Serialize.WriteReplace;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReplaceTest {
    public static void main(String[] args) {
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("./src/Serialize/WriteReplace/[自定义序列化替换对象]测试文件.txt"));
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("./src/Serialize/WriteReplace/[自定义序列化替换对象]测试文件.txt"));
            Person person=new Person("name",1);
            objectOutputStream.writeObject(person);
            ArrayList list=(ArrayList)objectInputStream.readObject();
            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
