package Serialize.ReadResolve;

import java.io.ObjectStreamException;
import java.io.Serializable;

//一个用来测试的类，构造器私有
public class TestClass implements Serializable{
    public static final TestClass test1=new TestClass(1);
    public static final TestClass test2=new TestClass(2);
    private int value;
    private TestClass(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    //添加读取时调用方法，反序列化的对象被丢弃
    private Object readResolve() throws ObjectStreamException{
        if(value==1){
            return test1;
        }
        if(value==2){
            return test2;
        }
        return null;
    }
}
