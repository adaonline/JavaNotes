package Serialize;

import java.io.Serializable;
//继承可序列化接口
public class NeedObject implements Serializable{
    private int id;
    private String name;

    public NeedObject(int id, String name) {
        System.out.println("这是这需要序列化的类的构造函数！");
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
