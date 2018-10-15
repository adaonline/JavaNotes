package Serialize.WriteReplace;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable{
    private String name;
    private  int sex;
    //构造器
    public Person(String name,int sex){
        System.out.println("序列化自定义替换初始化！");
        this.name=name;
        this.sex=sex;
    }
    //重新writeReplace方法，会在序列化对象之前，调用这个方法

    /**
     * 注意，
     * 1.实现了writeReplace就不要实现writeObject了，因为执行顺序是writeObject(writeReplace())；
     * 2.转变的类，需要也是可以序列化的，必须彻底序列化，引用什么的也要可序列化
     *
     */
    private Object writeReplace() throws ObjectStreamException{
        ArrayList<Object> list=new ArrayList<Object>();
        list.add(name);
        list.add(sex);
        return list;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
