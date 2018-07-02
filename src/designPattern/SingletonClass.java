package designPattern;

/**
 * Description:单例模式
 * User: zhangdanyang
 * Date: 2018/6/5 14:18.
 */
public class SingletonClass {
    //懒汉式,线程不安全
    /*private static SingletonClass instance;
    private SingletonClass(){}
    public static SingletonClass getInstance(){
        if(instance==null)
            instance=new SingletonClass();
        return instance;
    }*/
    //懒汉式，线程安全，效率低，99%不需要同步
    /*private static SingletonClass instance;
    private SingletonClass(){};
    public static synchronized SingletonClass getInstance(){
        if(instance==null)
            instance=new SingletonClass();
        return instance;
    }*/
    //饿汉式，线程安全，但是在类加载的时候就实例化
    /*private static SingletonClass instance=new SingletonClass();
    private SingletonClass(){};
    public static SingletonClass getInstance(){
        return instance;
    }*/
    //双检锁、双重锁校验（DCL,即double-checked locking）,安全，在多线程下保持高性能
   /* private volatile static SingletonClass instance;
    private SingletonClass(){};
    public static SingletonClass getInstance(){
        if(instance==null){
            synchronized(SingletonClass.class){
                if(instance==null){
                    instance=new SingletonClass();
                }            }
        }
        return instance;
    }*/
    //登记式、静态内部类
    private static class SingletonHodler{
        private static final SingletonClass INSTANCE=new SingletonClass();
    }
    private SingletonClass(){}
    public static final SingletonClass getInstance(){
        return SingletonHodler.INSTANCE;
    }

}
