package ProxyTest.DynaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvokationHandler implements InvocationHandler{
    //需要被代理的对象
    private Object target;
    public void setTarget(Object object){
        this.target=object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DogUtil dogUtil=new DogUtil();
        dogUtil.method1();
        Object result=method.invoke(target,args);
        dogUtil.method2();
        return result;
    }
}
