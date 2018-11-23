package ReflectTest.Methods;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ClassPoolByMethods {
    //定义一个对象池，前面是对象名，后面是实际对象
    private Map<String,Object> objectpool=new HashMap<>();
    private Properties config=new Properties();
    //从指定的文件中初始化配置对象
    public void init(String filename){
        try {
            FileInputStream fileInputStream=new FileInputStream(filename);
            config.load(fileInputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //根据字符串类名，创建对应java对象
    private Object createObject(String classname) throws InstantiationException,IllegalAccessException,ClassNotFoundException{
        //根据字符串获取对应的class对象，这时候会初始化对象，就会打印对象初始化的内容
        Class<?> clazz=Class.forName(classname);
        //调用默认构造器
        return clazz.newInstance();
    }
    public void initpool(){
        try {

            for(String name:config.stringPropertyNames()){
                if(!name.contains("%")){
                    //取出一个key value，就创建一个value对应的对象
                    objectpool.put(name,createObject(config.getProperty(name)));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initProperty() throws InvocationTargetException,IllegalAccessException,NoSuchMethodException{
        for (String name:config.stringPropertyNames()){
            //前一半为
            if(name.contains("%")){
                String[] obj_prop=name.split("%");
                Object targetObject=getObject(obj_prop[0]);
                String methodname="set"+obj_prop[1].substring(0,1).toUpperCase()+obj_prop[1].substring(1);
                Class<?> clazz=targetObject.getClass();
                //获取对应的有一个String参数的方法
                Method mtd=clazz.getMethod(methodname,String.class);
                mtd.invoke(targetObject,config.getProperty(name));
            }
        }
    }
    public Object getObject(String name){
        return objectpool.get(name);
    }

    public static void main(String[] args) throws Exception{
        ClassPoolByMethods classPoolByMethods=new ClassPoolByMethods();
        classPoolByMethods.init("src/ReflectTest/Methods/methodObj.txt");
        classPoolByMethods.initpool();
        classPoolByMethods.initProperty();
        System.out.println(classPoolByMethods.getObject("a"));
    }
}
