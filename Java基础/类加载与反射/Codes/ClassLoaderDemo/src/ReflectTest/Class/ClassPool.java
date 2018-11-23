package ReflectTest.Class;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
//生成类是默认构造器
public class ClassPool {
    //定义一个对象池
    private Map<String,Object> objectpool=new HashMap<>();
    //根据字符串类名，创建对应java对象
    private Object createObject(String classname) throws InstantiationException,IllegalAccessException,ClassNotFoundException{
        //根据字符串获取对应的class对象
        Class<?> clazz=Class.forName(classname);
        //调用默认构造器
        return clazz.newInstance();
    }
    public void initpool(String fileName) throws InstantiationException,IllegalAccessException,ClassNotFoundException{
        try {
            FileInputStream fileInputStream=new FileInputStream(fileName);
            Properties properties=new Properties();
            properties.load(fileInputStream);
            for(String name:properties.stringPropertyNames()){
                //取出一个key value，就创建一个value对应的对象
                objectpool.put(name,createObject(properties.getProperty(name)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Object getObject(String name){
        return objectpool.get(name);
    }

    public static void main(String[] args) throws Exception{
        ClassPool classPool=new ClassPool();
        classPool.initpool("src/ReflectTest/Class/obj.txt");
        System.out.println(classPool.getObject("1"));
        System.out.println(classPool.getObject("2"));
    }
}
