package ProxyTest.DynaProxy;

import java.lang.reflect.Proxy;

public class MyProxyFactory {
    public static Object getProxy(Object target){
        MyInvokationHandler handler=new MyInvokationHandler();
        handler.setTarget(target);
        //返回一个动态代理
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),handler);
    }
}
