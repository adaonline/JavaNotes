package Serialize.userDefinedSerialize;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
//自定义一个可以写入读取有特殊操作的累
public class Person implements Serializable{
    private String name;
    private int sex;
    public Person(String name,int sex){
        System.out.println("这里是参数构造器");
        this.name=name;
        this.sex=sex;
    }

    /**
     * 写入应该和读取的顺序一直，要不然会出问题
     * @param outputStream
     * @throws Exception
     */
    private void writeObject(ObjectOutputStream outputStream) throws Exception{
        //反转名字写入二进制流
        outputStream.writeObject(new StringBuffer(name).reverse());
        outputStream.writeInt(sex);
    }

    private void readObject(ObjectInputStream inputStream) throws Exception{
        //读取后再反转回来
        this.name=((StringBuffer)inputStream.readObject()).reverse().toString();
        this.sex=inputStream.readInt();
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
