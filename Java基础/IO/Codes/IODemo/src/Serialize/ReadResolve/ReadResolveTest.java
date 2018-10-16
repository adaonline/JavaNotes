package Serialize.ReadResolve;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadResolveTest {
    public static void main(String[] args) {
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(
                    new FileOutputStream("./src/Serialize/ReadResolve/[自定义序列化读取替换对象]测试文件.txt"));
            ObjectInputStream objectInputStream=new ObjectInputStream(
                    new FileInputStream("./src/Serialize/ReadResolve/[自定义序列化读取替换对象]测试文件.txt"));
            objectOutputStream.writeObject(TestClass.test1);
            TestClass testClass=(TestClass) objectInputStream.readObject();
            /**
             * 如果没有ReadResolve方法，
             * 这时候发现序列化出来的两个类竟然不是同一个类，这对单例来说是不正确的
             * 这时候就输出的是false，false
             * 如果ReadResolve有定义，修正了返回的对象，这时候就会是true，true
             */
            System.out.println(testClass.equals(TestClass.test1));
            System.out.println(testClass==TestClass.test1);
//            System.out.println(testClass.getValue()==TestClass.test1.getValue());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
