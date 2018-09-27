package StreamTest;

import java.io.FileWriter;

public class FileWriterTest {
    public static void main(String[] args) {
        try {
            FileWriter writer=new FileWriter("writer.txt");
            writer.write("这是FileWriter的测试类文件！！\n");
            writer.write("这是FileWriter的测试类文件！！\n");
            writer.write("这是FileWriter的测试类文件！！\n");
            writer.write("这是FileWriter的测试类文件！！\n");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
