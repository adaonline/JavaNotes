package stack.arraystack;

import java.io.Serializable;
import java.util.Arrays;

public class MyStack<T> implements Stack<T> ,Serializable{

    /**
     * 栈顶指针
     */
    private int top=-1;
    /**
     * 初始容量
     */
    private int capacity=5;
    /**
     * 存储数组
     */
    private T[] array;
    /**
     * 大小
     */
    private int size;

    /**
     * 默认初始化
     */
    public MyStack() {
        array=(T[])new Object[this.capacity];
    }

    /**
     * 初始化指定长度的栈
     * @param capacity
     */
    public MyStack(int capacity) {
        array=(T[])new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return top==-1;
    }

    @Override
    public boolean isFull() {
        return top+1==array.length;
    }

    public void expend(int newsize){
        T[] newarray=(T[])new Object[newsize];
        for(int i=0;i<size;i++){
            newarray[i]=array[i];
        }
        array=newarray;
        //top位置不变
    }
    /**
     * 传入插入数据，并且实现扩容
     * @param t
     */
    @Override
    public void push(T t) {
        if(isFull()){//用50%来扩容
            int newsize=size+(size>>1);
            expend(newsize);
        }
        top++;
        array[top]=t;
        size++;
    }

    @Override
    public T pop() {
        if(isEmpty()){
            return null;
        }else{
            size--;
            return array[top--];
        }


    }

    @Override
    public T peek() {
        if(isEmpty()){
            return null;
        }
        return array[top];
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "top=" + top +
                ", capacity=" + capacity +
                ", array=" + Arrays.toString(array) +
                ", size=" + size +
                ", array.length="+array.length+
                '}';
    }
}
