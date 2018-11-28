package ReflectTest.ArrayTest;

import java.lang.reflect.Array;

public class ArrayTest {
    public static void main(String[] args) {
        try{
            Object arr= Array.newInstance(String.class,10);
            Array.set(arr,5,"第五个元素");
            Array.set(arr,6,"第六个元素");

            Object name1=Array.get(arr,5);
            Object name2=Array.get(arr,6);
            System.out.println(name1);
            System.out.println(name2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
