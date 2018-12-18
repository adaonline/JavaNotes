package stack.arraystack;

public class main {
    public static void main(String[] args) {
        MyStack<Integer> s=new MyStack<Integer>();
        System.out.println(s.toString());
        for(int i=0;i<20;i++){
            s.push(i);
        }
        System.out.println(s.toString());
        for(int i=0;i<10;i++){
            s.pop();
        }
        System.out.println(s.toString());//其实pop只是把top往回移动
    }
}
