package StreamTest;


import java.io.FileReader;
//读取字符
public class FileReaderTest {
    public static void main(String[] args) {
        try {
            //字符输入流
            FileReader reader=new FileReader("./src/StreamTest/FileReaderTest.java");
            char[] buf=new char[20];
            //保存读到的字符数目
            int readed=0;
            //循环读取
            while ((readed=reader.read(buf))>0){
                System.out.print(new String(buf,0,readed));
            }
            //try可以自动关闭资源
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
