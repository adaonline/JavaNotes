package ReflectTest.Methods;

public class A {
    public A() {
        System.out.println("初始化A");

    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return name;
    }
}
