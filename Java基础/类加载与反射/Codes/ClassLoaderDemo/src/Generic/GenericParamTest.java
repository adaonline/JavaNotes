package Generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
//通过反射查看泛型类型信息
public class GenericParamTest {
    private HashMap<String,Integer> ss;

    public static void main(String[] args) throws Exception{
        Class<GenericParamTest> cls=GenericParamTest.class;
        Field field=cls.getDeclaredField("ss");
        //直接获取对应的类型只会对普通的成员变量有效
        Class<?> a=field.getType();
        System.out.println("普通类型是"+a);
        //获取泛型变量
        Type type=field.getGenericType();
        //如果type类型是ParameterizedType对象
        if(type instanceof ParameterizedType){
            ParameterizedType parameterizedType=(ParameterizedType)type;
            //获取原始类型
            System.out.println("原始类型是"+parameterizedType.getRawType());
            //获取泛型类型的泛型参数
            Type[] typeargs=parameterizedType.getActualTypeArguments();
            System.out.println("输出泛型信息：");
            for(int i=0;i<typeargs.length;i++){
                System.out.println("第"+i+"个泛型类型是"+typeargs[i]);
            }
        }else{
            System.out.println("出错");

        }
    }
}
