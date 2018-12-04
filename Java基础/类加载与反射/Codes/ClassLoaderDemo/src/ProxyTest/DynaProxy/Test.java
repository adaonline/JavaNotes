package ProxyTest.DynaProxy;

public class Test {
    public static void main(String[] args) {
        Dog target=new GunDog();
        Dog dog=(Dog)MyProxyFactory.getProxy(target);
        dog.info();
        dog.run();
    }
}
