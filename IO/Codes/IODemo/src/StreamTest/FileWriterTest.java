package StreamTest;

import java.io.FileWriter;

public class FileWriterTest {
    public static void main(String[] args) {
        try {
            FileWriter writer=new FileWriter("FileWriterTest测试文件.txt");
            writer.write("这是FileWriter的测试类文件！！\n");
            writer.write("这是FileWriter的测试类文件！！\n");
            writer.write("这是FileWriter的测试类文件！！\n");
            writer.write("这是FileWriter的测试类文件！！\n");
            //不执行close的话，则新建的文件是空的
            // 一定要记得去close这样才会执行flush刷新内存中的数据到物理存储中，

            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
