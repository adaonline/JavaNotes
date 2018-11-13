package LoaderTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class CompileClassLoader extends ClassLoader{
    private byte[] getbytes(String filename) throws IOException{
        File file=new File(filename);
        long len=file.length();
        byte[] raw=new byte[(int) len];
        FileInputStream fin=new FileInputStream(file);
        int r=fin.read(raw);
        if(r!=len){
            throw new IOException("读取不完全");
        }
        return raw;

    }
    //编译指定文件
    private boolean compile(String javaFile) throws IOException{
        System.out.println("编译："+javaFile);
        Process p=Runtime.getRuntime().exec("javac "+javaFile);
        try {
            //等这个线程完成
            p.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
        //运行返回值
        int ret=p.exitValue();
        return ret==0;
    }
    protected Class<?> findClass(String name)throws ClassNotFoundException{
        Class clazz=null;
        String filesub=name.replace(".","/");
        String javaFilename=filesub+".java";
        String classFilename=filesub+".class";
        File javaFile=new File(javaFilename);
        File classFile=new File(classFilename);
        //如果指定的java文件存在但是class文件不存在，或者java源文件的修改时间比class修改时间更晚，重新编译
        if(javaFile.exists()&&(!classFile.exists()||javaFile.lastModified()>classFile.lastModified())){
            try {
                if(!compile(javaFilename)||!classFile.exists()){
                    throw  new ClassNotFoundException("ClassNotFoundException"+javaFilename);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //如果class文件存在，系统负责将文件转换成class对象
        if(classFile.exists()){
            try {
               // System.out.println(name.split(".")[0]);
                byte[] raw=getbytes(classFilename);
                clazz=defineClass("BeloadClass",raw,0,raw.length);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if(clazz==null){
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    public static void main(String[] args)throws Exception {
        CompileClassLoader compileClassLoader=new CompileClassLoader();
        Class<?> clazz=compileClassLoader.loadClass("LoaderTest.BeloadClass");
        Method main=clazz.getMethod("out");
        main.invoke(null);

    }
}
