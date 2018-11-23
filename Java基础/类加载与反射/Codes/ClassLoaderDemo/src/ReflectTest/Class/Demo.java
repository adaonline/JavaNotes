package ReflectTest.Class;

public class Demo {
    private String name;
    public Demo() {
    }

    public Demo(String name) {
        this.name = name;
    }

    public void out(){
        System.out.println("名字是"+name);
    }
}
