package my.demo;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @program: test
 * @description: 热部署
 * @author: ming.cao
 * @create: 2020-11-09 14:49
 **/
public class HotWap {
    public static void main(String[] args) throws Exception{
        loadHelloWorld();
        //回收资源，释放helloWorld.class文件，使之被代替
        System.gc();
        Thread.sleep(3000);//等待资源被回收
        File fileV2 = new File("Demo.class");
        File fileV1 = new File("F:\\selfStudy\\springboot\\test\\out\\production\\test\\my\\demo\\Demo.class");
        fileV1.delete();//删除V1版本
        fileV2.renameTo(fileV1);//更新V2版本
        System.out.println("Update success!");
        loadHelloWorld();

    }

    public static void loadHelloWorld() throws Exception{
        //自定义类加载器
        MyClassLoader myLoader = new MyClassLoader();
        //类实例
        Class<?> class1 = myLoader.findClass("my.demo.Demo");
        //生产新的对象
        Object obj1 = class1.newInstance();
        Method method = class1.getMethod("say");
        //执行方法say
        method.invoke(obj1);
        //对象
        System.out.println(obj1.getClass());
        //对象的类加载器
        System.out.println(obj1.getClass().getClassLoader());
    }
}
