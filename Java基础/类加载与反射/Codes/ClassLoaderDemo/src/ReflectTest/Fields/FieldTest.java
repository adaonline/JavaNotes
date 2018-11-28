package ReflectTest.Fields;

import java.lang.reflect.Field;

public class FieldTest {

    public static void main(String[] args) throws Exception{
        Person p=new Person();

        Class<Person> personClass=Person.class;
        //获取成员变量
        Field namefield=personClass.getDeclaredField("name");
        //设置反射访问该成员变量时，取消访问权限检查
        namefield.setAccessible(true);
        namefield.set(p,"mingzi");
        Field agefield=personClass.getDeclaredField("age");
        agefield.setAccessible(true);
        agefield.setInt(p,20);
        System.out.println(p);
    }
}
class Person{
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
