package Generic;

import java.lang.reflect.Array;

public class GenericArray {
    //对Array的newInstance方法进行包装

    public static <T> T[] newInstance(Class<T> componentType,int length){
        return (T[]) Array.newInstance(componentType,length);
    }

    public static void main(String[] args) {
        //创建一维数组
        String[] arr=GenericArray.newInstance(String.class,10);

        int[][] intarr=GenericArray.newInstance(int[].class,5);
        arr[5]="这是一维数组的第五个";
        intarr[1]=new int[]{1,2};
        System.out.println(arr[5]);
        System.out.println(intarr[1][1]);
    }
}
