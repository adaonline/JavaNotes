package StreamTest;

import java.io.StringReader;
import java.io.StringWriter;

//使用字符串作为构造节点去构造一个流
public class StringAsNodeTest {
    public static void main(String[] args) {
        String s="这是一个用来构造流的字符串\n"+
                "采用的StringReader\n";
        try {
            //字符就用char
            char[] buff=new char[32];
            int readed=0;
            StringReader reader=new StringReader(s);
            while ((readed=reader.read(buff))>0){
                System.out.print(new String(buff,0,readed));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("----------------下面是输出流---------------------");
        try {
            //StringWriter是用一个StringBuffer作为输出的，因为String是不可变对象
            StringWriter writer=new StringWriter();
            writer.write("这是一个String输出流");
            writer.write("最终是一个StringBuffer");
            System.out.println(writer.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
