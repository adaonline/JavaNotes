package ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest{

    public static void main(String[] args) throws Exception{
        InvocationHandler handler=new MyinvokationHandler();
        Person p= (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),new Class[]{Person.class},handler);
        p.walk();
        p.sayHello("你好啊");
    }


}
interface Person{
    void walk();
    void sayHello(String name);
}
class MyinvokationHandler implements InvocationHandler{
    /**
     *
     * @param proxy 动态代理对象
     * @param method 正在执行的方法
     * @param args 调用目标方法传入的实参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行方法："+method);
        if(args!=null){
            System.out.println("传入的实参为：");
            for(Object val:args){
                System.out.println(val);
            }
        }else{
            System.out.println("没有实参");
        }
        return null;
    }
}
