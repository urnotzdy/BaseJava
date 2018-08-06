package thread;

import java.util.Random;

/**
 * Description:
 * User: zhangdanyang
 * Date: 2018/6/12 10:04.
 */
public class ThreadLocal2Class {
    public static void main(String[] args){
        for (int i=0;i<2;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int num = new Random().nextInt();
                    //将对象的创建放到对象内部操作
                    MyThreadScopeData.getThreadInstance().setName(Thread.currentThread().getName());
                    MyThreadScopeData.getThreadInstance().setAge(num);
                    System.out.println("线程" + Thread.currentThread().getName() + "设置的数据是" + num);
                    new A().get();
                }
            }).start();
        }
    }
    static class A{
        public void get(){
            MyThreadScopeData myThreadScopeData=MyThreadScopeData.getThreadInstance();
            System.out.println("线程"+myThreadScopeData.getName()+"获得的数据是"+myThreadScopeData.getAge());
        }
    }
}
class MyThreadScopeData{
    //创建成单例
   /* private static MyThreadScopeData instance=null;
    private MyThreadScopeData(){}
    public  static synchronized MyThreadScopeData getInstance(){
        if(instance==null){
            instance=new MyThreadScopeData();
        }
        return instance;
    }*/

    //一个线程有一个容器对象
    private static ThreadLocal<MyThreadScopeData> map=new ThreadLocal<MyThreadScopeData>();//每个线程有一个实例
    private MyThreadScopeData(){}
    public static MyThreadScopeData getThreadInstance(){//一个线程有一个容器对象，所以不需要synchronized
        MyThreadScopeData instanceLocal=map.get();
        if(instanceLocal==null){
            instanceLocal=new MyThreadScopeData();
            map.set(instanceLocal);
        }
        return instanceLocal;
    }
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}