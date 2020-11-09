package my.demo;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ClassLoader;

/**
 * @program: test
 * @description: 自定义类加载器。-去加载更新后的class类文件。并override findClass方法
 * @author: ming.cao
 * @create: 2020-11-09 14:40
 **/

public class MyClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
            InputStream is = this.getClass().getResourceAsStream(fileName);
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name,b,0,b.length);
        }catch(IOException e){
            throw new ClassNotFoundException(name);
        }
      //  return super.findClass(name);
    }
}
