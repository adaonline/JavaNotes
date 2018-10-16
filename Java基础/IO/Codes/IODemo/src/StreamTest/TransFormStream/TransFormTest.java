package StreamTest.TransFormStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//转换流的用法
public class TransFormTest {
    public static void main(String[] args) {
        try {
            //获取键盘输入，将输入对象转换成Reader对象
            InputStreamReader reader=new InputStreamReader(System.in);
            //包装一下子
            BufferedReader bufferedReader=new BufferedReader(reader);
            String line=null;
            //readline阻塞一直读到有换行符\n为止
            while ((line=bufferedReader.readLine())!=null){
                if(line.equals("quit")){//退出操作
                    System.exit(1);
                }
                System.out.println("输入内容:"+line);


            }
            bufferedReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
