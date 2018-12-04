package Generic;

public class NormalObjectFactory1 {
    //返回的是一个Object，但是如果强制转换的类不对则会抛出异常
    public static Object getInstance(String className){
        try {
            //创建指定的class对象
            Class cls=Class.forName(className);
            return cls.newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
