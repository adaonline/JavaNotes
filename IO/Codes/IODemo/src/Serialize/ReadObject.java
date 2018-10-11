package Serialize;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadObject {
    public static void main(String[] args) {
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("序列化测试文件.txt"));
            NeedObject needObject=(NeedObject)objectInputStream.readObject();
            System.out.println("读取到的内容为:");
            System.out.println("id："+needObject.getId());
            System.out.println("name："+needObject.getName());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
