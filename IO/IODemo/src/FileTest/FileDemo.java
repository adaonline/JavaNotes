package FileTest;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        try {
            File f=new File("new file");
            System.out.println(f.getName());
            System.out.println(f.getAbsolutePath());//获取绝对路径
            System.out.println(f.getParent());//获取相对路径的父亲路径，为null
            f.createNewFile();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
