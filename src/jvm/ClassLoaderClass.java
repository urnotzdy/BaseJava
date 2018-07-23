package jvm;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/7/11 14:43.
 */
public class ClassLoaderClass {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader=ClassLoaderClass.class.getClassLoader();
        System.out.println("类加载器："+loader);
        loader.loadClass("jvm.Test");//使用classloader加载类，不会执行初始化
        Class.forName("jvm.Test");//使用Class.forName()将类的.class文件加载到jvm中，对类进行初始化
        Class.forName("jvm.Test",false,loader);
    }
}
