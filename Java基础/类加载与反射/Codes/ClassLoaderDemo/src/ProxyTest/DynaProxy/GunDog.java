package ProxyTest.DynaProxy;

public class GunDog implements Dog {
    @Override
    public void info() {
        System.out.println("这是一只狗");
    }

    @Override
    public void run() {
        System.out.println("在奔跑");
    }
}
