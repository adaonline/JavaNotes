package ReflectTest.Class;

import java.lang.reflect.Constructor;

public class ReflectDemo {
    public static void main(String[] args) throws Exception{
        Class<?> clazz=Class.forName("ReflectTest.Class.Demo");
        Constructor constructor=clazz.getConstructor(String.class);
        Object obj=constructor.newInstance("测试类");
        ((Demo)obj).out();
    }
}
