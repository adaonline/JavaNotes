package StreamTest.PushBack;

import java.io.FileReader;
import java.io.PushbackReader;

public class PushBackStreamTest {
    public static void main(String[] args) {
        try {
            //创建推回reader，缓冲区100
            PushbackReader pushbackReader=new PushbackReader(
                    new FileReader("./src/StreamTest/PushBack/PushBackStreamTest.java"),100);
            //缓冲区应该是读取buf长度的两倍以上，要不然读取时候上一次加这一次的推回会导致Pushback buffer overflow的异常，溢出了
            char[] buf=new char[50];
            String lastread="";
            int readed=0;
            while ((readed=pushbackReader.read(buf))>0){

                String texr=new String(buf,0,readed);
                //需要查找的字符串的位置
                int index=0;
                if((index=(lastread+texr).indexOf("PushBackStreamTest"))>0){
                    //如果找到了内容，将上次读取的加上这次读取的推回缓冲区
                    pushbackReader.unread((lastread+texr).toCharArray());
                    //这时候的推回缓冲区里的内容是上次加这次的，index是要寻找字符串的下标，我们只需要重新定义下标就可以控住读取到哪里了
                    buf=new char[index];
                    //重新读取
                    pushbackReader.read(buf,0,index);
                    System.out.println(new String(buf,0,index));
                    System.exit(0);
                }else{
                    //第一次读取的时候，这里的lasteraed是空的，所以后续能完整的打印不会重复
                    System.out.println(lastread);
                    lastread=texr;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
