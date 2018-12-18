package stack.linkedStack;

import stack.arraystack.Stack;

public class LinkedStack<T> implements Stack<T> {

    private  Node<T> top;
    private int size;

    public LinkedStack() {
        this.top = new Node<>();
    }

    @Override
    public boolean isEmpty() {
        return top==null;
    }

    @Override
    public void push(T t) {
        if(top==null){
            this.top=new Node<>(t);
        }else if(this.top.getData()==null){
            this.top.setData(t);
        }else{
            Node p=new Node(t);
            p.setNext(top.getNext());
            top.setNext(p);

        }
        size++;
    }

    @Override
    public T pop() {
        T data=top.getData();
        top=top.getNext();
        size--;
        return data;
    }

    @Override
    public T peek() {
        return top.getData();
    }

    /**
     * 链式栈基本是不会满的
     * @return
     */
    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public String toString() {
        return "LinkedStack{" +
                "top=" + top.toString() +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        LinkedStack<Integer> s=new LinkedStack<Integer>();
        System.out.println(s.toString());
        for(int i=0;i<20;i++){
            s.push(i);
        }
        System.out.println(s.toString());
        for(int i=0;i<10;i++){
            s.pop();
        }
        System.out.println(s.toString());
    }
}
