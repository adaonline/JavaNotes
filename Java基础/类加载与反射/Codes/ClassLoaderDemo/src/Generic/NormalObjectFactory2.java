package Generic;

import java.util.Date;

public class NormalObjectFactory2 {
    public static <T> T getInstance(Class<T> cls){
        try {
            return cls.newInstance();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        //得到实例后不用类型转换
        Date d=NormalObjectFactory2.getInstance(Date.class);
    }
}
