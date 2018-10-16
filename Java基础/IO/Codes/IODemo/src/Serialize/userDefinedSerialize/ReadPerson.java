package Serialize.userDefinedSerialize;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

//反序列化类
public class ReadPerson {
    public static void main(String[] args) {
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("./src/Serialize/userDefinedSerialize/[自定义序列化]测试文件.txt"));
            Person needObject=(Person)objectInputStream.readObject();
            System.out.println("读取到的内容为:");
            System.out.println("sex："+needObject.getSex());
            System.out.println("name："+needObject.getName());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
